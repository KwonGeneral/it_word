package com.kwon.it_word.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kwon.it_word.data.LocalData

@Dao
interface LocalService {
    @Query("SELECT * FROM tb_local")
    fun LocalReadAll(): List<LocalData>

    @Insert
    fun LocalCreate(vararg Local: LocalData)

    @Query("SELECT * FROM tb_Local WHERE question = :question")
    fun LocalSearch(question: String): List<LocalData>

    @Query("DELETE FROM tb_Local WHERE id = :id")
    fun LocalDelete(id: Long)

    @Query("DELETE FROM tb_Local")
    fun LocalReset()
}