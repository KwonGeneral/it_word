package com.kwon.it_word.db

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import java.lang.Exception

class SharedDB(val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: SharedDB? = null
        fun getInstance(context: Context): SharedDB? {
            instance?.let {
                return it
            }
            instance = SharedDB(context)
            return instance!!
        }
    }

    fun getString(key: String, defValue: String): String {
        return prefs.getString(key, defValue).toString()
    }

    fun setString(key: String, str: String) {
        prefs.edit().putString(key, str).apply()
    }

    fun getFcmToken(): String {
        return prefs.getString("fcm_token", "").toString()
    }

    fun setFcmToken(fcm_token: String) {
        prefs.edit().putString("fcm_token", fcm_token).apply()
    }

    fun isAutoLogin(): Boolean {
        return false
    }
}

