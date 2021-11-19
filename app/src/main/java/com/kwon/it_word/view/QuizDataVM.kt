package com.kwon.it_word.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kwon.it_word.data.QuizData
import com.kwon.it_word.db.LocalDataBase

class QuizDataVM constructor(private val context: Context) {
    var quizReadData = MutableLiveData<List<QuizData>>()

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: QuizDataVM? = null

        @JvmName("fragment_getInstance")
        fun getInstance(context: Context): QuizDataVM {
            instance?.let {
                return it
            }
            instance = QuizDataVM(context)
            return instance!!
        }
    }

    fun onQuizCreate(answer:String, question_1:String, question_2:String, question_3:String, question_4:String) {
        LocalDataBase.getInstance(context)?.QuizService()?.apply {
            quizCreate(
                QuizData(
                    id = null,
                    answer = answer,
                    question_1 = question_1,
                    question_2 = question_2,
                    question_3 = question_3,
                    question_4 = question_4,
                    view_status = false,
                    correct_status = false
                )
            )
//            quizReadData.postValue(quizReadAll())
            Log.d("TEST", "생성~!! : ${quizReadData.value}")
        }
    }

    fun onQuizRead() {
        LocalDataBase.getInstance(context)?.QuizService()?.quizRead()?.let {
            quizReadData.postValue(it)
            Log.d("TEST", "읽기~!! : $it")
        }
    }

    fun onQuizUpdate(quizData: QuizData) {
        LocalDataBase.getInstance(context)?.QuizService()?.quizUpdate(quizData)?.let {
            Log.d("TEST", "수정 완료~!! : $it")
        }
    }

    fun onQuizDelete(id: Long) {
        LocalDataBase.getInstance(context)?.QuizService()?.quizDelete(id)
        quizReadData.value = LocalDataBase.getInstance(context)?.QuizService()?.quizReadAll()
        Log.d("TEST", "삭제~!! : ${quizReadData.value}")
    }

    fun onQuizSearch() {

    }
}