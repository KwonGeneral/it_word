package com.kwon.kotlinquiz.Quiz.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.kwon.it_word.data.QuizData
import kotlinx.android.synthetic.main.list_item_quiz.view.*

class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: QuizData) {
        with(itemView) {
            if(!data.view_status) {
                quiz_question_text.text = data.answer
                quiz_one_btn.text = data.question_1
                quiz_two_btn.text = data.question_2
                quiz_three_btn.text = data.question_3
                quiz_four_btn.text = data.question_4

                quiz_main_layout.setOnClickListener {
                    Snackbar.make(it, "아이템 $layoutPosition 터치!", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setAlpha(alpha: Float) {
        with(itemView) {
            quiz_main_layout.alpha = alpha
        }
    }

}