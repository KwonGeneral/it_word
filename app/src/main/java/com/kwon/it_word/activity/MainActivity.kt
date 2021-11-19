package com.kwon.it_word.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.kwon.it_word.R
import com.kwon.it_word.contain.ColorDefine
import com.kwon.it_word.contain.FragmentDefine.Companion.CHAT_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.HOME_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.MYPAGE_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.NOTE_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.QUIZ_FRAGMENT
import com.kwon.it_word.contain.FragmentDefine.Companion.SETTING_FRAGMENT
import com.kwon.it_word.view.FireBaseVM
import com.kwon.it_word.view.ScreenVM
import com.kwon.it_word.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavList: ArrayList<ImageView> = arrayListOf(bottom_nav_home, bottom_nav_note, bottom_nav_quiz, bottom_nav_mypage)

        FireBaseVM(this).let { fm ->
            fm.createFcm()
            fm.fcmStatusMessage.observe(this, { ob ->
                Toast.makeText(applicationContext, ob, Toast.LENGTH_SHORT).show()
            })
        }

        ScreenVM.getInstance(this).screenStatus.observe(this, { fragment_name ->
            for(k in bottomNavList) {
                k.backgroundTintList = ColorStateList.valueOf(Color.parseColor(ColorDefine.BOTTOM_NAV_DIS_ACTIVE.color))
            }
            when(fragment_name) {
                HOME_FRAGMENT -> { bottom_nav_home.backgroundTintList = ColorStateList.valueOf(Color.parseColor(ColorDefine.BOTTOM_NAV_ACTIVE.color)) }
                NOTE_FRAGMENT -> { bottom_nav_note.backgroundTintList = ColorStateList.valueOf(Color.parseColor(ColorDefine.BOTTOM_NAV_ACTIVE.color)) }
                QUIZ_FRAGMENT -> { bottom_nav_quiz.backgroundTintList = ColorStateList.valueOf(Color.parseColor(ColorDefine.BOTTOM_NAV_ACTIVE.color)) }
                MYPAGE_FRAGMENT -> { bottom_nav_mypage.backgroundTintList = ColorStateList.valueOf(Color.parseColor(ColorDefine.BOTTOM_NAV_ACTIVE.color)) }
            }
            changeFragment(fragment_name)
        })

        bottom_nav_home_layout.setOnClickListener { ScreenVM.getInstance(this).screenStatus.value = HOME_FRAGMENT }
        bottom_nav_note_layout.setOnClickListener { ScreenVM.getInstance(this).screenStatus.value = NOTE_FRAGMENT }
        bottom_nav_quiz_layout.setOnClickListener { ScreenVM.getInstance(this).screenStatus.value = QUIZ_FRAGMENT }
        bottom_nav_mypage_layout.setOnClickListener { ScreenVM.getInstance(this).screenStatus.value = MYPAGE_FRAGMENT }
    }

    private fun changeFragment(fragment_type:String) {
        supportFragmentManager.beginTransaction().let { ft ->
            fragment_type.let { ty ->
                when (ty) {
                    HOME_FRAGMENT -> HomeFragment().apply { ft.replace(R.id.main_frag, this).commit() }
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

