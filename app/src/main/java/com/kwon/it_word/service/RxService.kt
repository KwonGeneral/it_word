package com.kwon.it_word.service

import android.content.Context
import com.google.gson.GsonBuilder
import com.kwon.it_word.db.SharedDB
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RxService {
    companion object {
        fun <T> create(context: Context, service: Class<T>): T {
            return connect(context).create(service) as T
        }
        private fun connect(context: Context): Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(GsonBuilder().setLenient().create())
                )
                .baseUrl("url 입력")
                .build()
        }
    }
}