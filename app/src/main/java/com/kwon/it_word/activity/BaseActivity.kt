package com.kwon.it_word.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kwon.it_word.R

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}