package com.kwon.it_word.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.kwon.it_word.R
import com.kwon.it_word.contain.Define.Companion.QUESTION
import com.kwon.it_word.contain.FireBaseDefine
import com.kwon.it_word.contain.FragmentDefine.Companion.CHAT_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.HOME_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.MYPAGE_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.NOTE_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.QUIZ_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.SETTING_FRAGMENT
import com.kwon.it_word.view.FireBaseVM
import com.kwon.it_word.view.ScreenVM
import com.kwon.it_word.widget.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FireBaseVM(this).let { fm ->
            fm.createFcm()
            fm.fcmStatusMessage.observe(this, { ob ->
                Toast.makeText(applicationContext, ob, Toast.LENGTH_SHORT).show()
            })
        }

        ScreenVM.getInstance(this).screenStatus.observe(this, { fragment_name ->
            changeFragment(fragment_name)
        })
    }

    // Fragment 전환
    private fun changeFragment(fragment_type:String) {
        supportFragmentManager.beginTransaction().let { ft ->
            fragment_type.let { ty ->
                when (ty) {
                    QUIZ_FRAGMENT -> QuizFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    CHAT_FRAGMENT -> ChatFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    NOTE_FRAGMENT -> NoteFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    MYPAGE_FRAGMENT -> MypageFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    SETTING_FRAGMENT -> SettingFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                    else -> QuizFragment().apply { ft.replace(R.id.main_frag, this).commit() }
                }
            }
        }
    }
}

