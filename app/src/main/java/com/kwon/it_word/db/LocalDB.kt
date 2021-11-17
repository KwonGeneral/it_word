package com.kwon.it_word.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kwon.it_word.data.LocalData
import com.kwon.it_word.service.LocalService

@Database(entities = [LocalData::class], version = 2, exportSchema = false)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun LocalService(): LocalService

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
                "local_db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return instance
        }
    }
}

