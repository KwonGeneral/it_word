package com.kwon.it_word.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kwon.it_word.data.QuizData
import com.kwon.it_word.service.QuizService

@Database(entities = [QuizData::class], version = 1, exportSchema = false)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun QuizService(): QuizService

    companion object {
        var instance: LocalDataBase? = null

        @Synchronized
        fun getInstance(context: Context): LocalDataBase? {
            instance?.let {
                return it
            }
            instance = Room.databaseBuilder(
                context.applicationContext,
                LocalDataBase::class.java,
                "quiz_db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return instance
        }
    }
}

