package com.kwon.it_word.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kwon.it_word.R
import com.kwon.it_word.data.QuizData
import com.kwon.it_word.view.QuizDataVM
import com.kwon.kotlinquiz.Quiz.Adapter.QuizDiff
import com.kwon.kotlinquiz.Quiz.Adapter.QuizViewHolder
import java.util.*

class QuizAdapter constructor(var context: Context): ListAdapter<QuizData, RecyclerView.ViewHolder>(QuizDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuizViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_quiz, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is QuizViewHolder) {
            holder.bind(getItem(position) as QuizData)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    // 아이템 이동
//    fun moveItem(fromPosition: Int, toPosition: Int) {
//        currentList.toMutableList()?.let {
//            Collections.swap(it, fromPosition, toPosition)
//            submitList(it)
//        }
//    }

    // 아이템 삭제 및 DB 삭제
    fun removeItem(position: Int) {
        currentList.toMutableList()?.let {
            QuizDataVM.getInstance(context)?.onQuizDelete(it[position]?.id!!.toLong())
            it.removeAt(position)
            submitList(it)
        }
    }
}