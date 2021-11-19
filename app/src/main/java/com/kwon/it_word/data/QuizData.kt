package com.kwon.it_word.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwon.it_word.contain.Define.Companion.EMPTY_STR

@Entity(tableName = "tb_quiz")
data class QuizData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "answer") // 문제
    val answer: String? = EMPTY_STR,

    @ColumnInfo(name = "question_1") // 문제 1
    val question_1: String? = EMPTY_STR,

    @ColumnInfo(name = "question_2") // 문제 2
    val question_2: String? = EMPTY_STR,

    @ColumnInfo(name = "question_3") // 문제 3
    val question_3: String? = EMPTY_STR,

    @ColumnInfo(name = "question_4") // 문제 4
    val question_4: String? = EMPTY_STR,

    @ColumnInfo(name = "view_status") // 문제 출제 여부
    var view_status: Boolean = false,

    @ColumnInfo(name = "correct_status") // 정답 여부
    var correct_status: Boolean = false,
)