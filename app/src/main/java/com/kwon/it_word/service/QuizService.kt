package com.kwon.it_word.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kwon.it_word.data.QuizData

@Dao
interface QuizService {
    @Query("SELECT * FROM tb_quiz")
    fun quizReadAll(): List<QuizData>

    @Query("SELECT * FROM tb_quiz WHERE view_status = 0")
    fun quizRead(): List<QuizData>

    @Insert
    fun quizCreate(vararg Quiz: QuizData)

    @Query("SELECT * FROM tb_quiz WHERE answer = :answer")
    fun quizSearch(answer: String): List<QuizData>

    @Query("DELETE FROM tb_quiz WHERE id = :id")
    fun quizDelete(id: Long)

    @Query("DELETE FROM tb_quiz")
    fun quizReset()

    @Update
    fun quizUpdate(vararg board: QuizData)
}