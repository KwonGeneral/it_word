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
import com.kwon.it_word.db.FireStoreDB
import com.kwon.it_word.db.SharedDB
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FireBaseVM(val context: Context) {
    var fcmStatusMessage = MutableLiveData<String>()
    var fcmList = mutableListOf<String>()

    init {
        fcmStatusMessage.postValue(FIREBASE_FCM_CREATING)
        fcmList.clear()
    }

    fun createFcm() {
        // FCM 토큰 생성 & Firebase DB 저장
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            FirebaseFirestore.getInstance().collection(FIREBASE_STORE_COLLECTION_USER).let { db ->
                db.get().addOnCompleteListener { read ->
                    if (read.isSuccessful) {
                        for (document in read.result!!) {
                            fcmList.add(document.data[FIREBASE_STORE_DATA_FCM_TOKEN].toString())
                        }

                        task.result.toString().let { fcm ->
                            if(fcm in fcmList) {
                                return@addOnCompleteListener
                            }
                            LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateDefine.DEFAULT.format))?.let { time ->
                                Build.MODEL?.let { model ->
                                    db.add(FireStoreDB(fcm, model, time)).apply {
                                        addOnSuccessListener {
                                            fcmStatusMessage.postValue(FIREBASE_FCM_CREATE_SUCCESS)
                                            SharedDB.getInstance(context)?.setString(FIREBASE_STORE_DATA_FCM_TOKEN, fcm)
                                        }
                                        addOnFailureListener {
                                            fcmStatusMessage.postValue(FIREBASE_FCM_CREATE_FAIL)
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