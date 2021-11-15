package com.kwon.it_word.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kwon.it_word.contain.Define.Companion.EMPTY_STR

@Entity(tableName = "tb_local")
data class LocalData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,

    @ColumnInfo(name = "question") // 문제
    val question: String? = EMPTY_STR,

    @ColumnInfo(name = "answer") // 정답
    val answer: String? = EMPTY_STR,
)