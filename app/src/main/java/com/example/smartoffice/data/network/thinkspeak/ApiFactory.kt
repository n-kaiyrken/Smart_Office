package com.example.smartoffice.data.network.thinkspeak

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://api.thingspeak.com/"

    val apiKey = "JYY2LMKB25152SU1"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService by lazy { retrofit.create(ApiService::class.java) }
}