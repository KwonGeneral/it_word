package com.kwon.it_word.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.kwon.it_word.contain.FragmentDefine.Companion.CHAT_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.HOME_FRAGMENT

class ScreenVM(val context: Context) {
    var screenStatus = MutableLiveData<String>()

    init {
        screenStatus.postValue(HOME_FRAGMENT)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: ScreenVM? = null
        fun getInstance(context: Context): ScreenVM {
            instance?.let {
                return it
            }
            instance = ScreenVM(context)
            return instance!!
        }
    }
}

