package com.kwon.it_word.view

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.kwon.it_word.contain.DateDefine
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_FCM_CREATE_FAIL
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_FCM_CREATE_SUCCESS
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_FCM_CREATING
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_STORE_COLLECTION_USER
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_STORE_DATA_CREATED_AT
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_STORE_DATA_FCM_TOKEN
import com.kwon.it_word.contain.FireBaseDefine.Companion.FIREBASE_STORE_DATA_PHONE_MODEL
import com.kwon.it_word.db.SharedDB
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FireBaseVM(val context: Context) {
    var fcm_status_message = MutableLiveData<String>()
    var fcm_list = mutableListOf<String>()

    init {
        fcm_status_message.postValue(FIREBASE_FCM_CREATING)
        fcm_list.clear()
    }

    fun createFcm() {
        // FCM 토큰 생성 & Firebase DB 저장
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("TEST", "tasktask : ${task.isSuccessful}")
                return@OnCompleteListener
            }
            FirebaseFirestore.getInstance().collection(FIREBASE_STORE_COLLECTION_USER)?.let { db ->
                Log.d("TEST", "db : ${db}")
                db.get().addOnCompleteListener { read ->
                    Log.d("TEST", "read : ${read}")
                    if (read.isSuccessful) {
                        for (document in read.result!!) {
                            fcm_list.add(document.data[FIREBASE_STORE_DATA_FCM_TOKEN].toString())
                        }

                        task.result.toString()?.let { fcm ->
                            Log.d("TEST", "fcm : ${fcm}")
                            if(fcm in fcm_list) {
                                return@addOnCompleteListener
                            }
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateDefine.DEFAULT.format))?.let { time ->
                                Build.MODEL?.let { model ->
                                    val user: MutableMap<String, Any> = HashMap()
                                    user[FIREBASE_STORE_DATA_FCM_TOKEN] = fcm
                                    user[FIREBASE_STORE_DATA_PHONE_MODEL] = model
                                    user[FIREBASE_STORE_DATA_CREATED_AT] = time

                                    db.add(user)?.apply {
                                        addOnSuccessListener {
                                            fcm_status_message.postValue(FIREBASE_FCM_CREATE_SUCCESS)
                                            SharedDB.getInstance(context)?.setString(FIREBASE_STORE_DATA_FCM_TOKEN, fcm)
                                        }
                                        addOnFailureListener {
                                            fcm_status_message.postValue(FIREBASE_FCM_CREATE_FAIL)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        })
    }
}