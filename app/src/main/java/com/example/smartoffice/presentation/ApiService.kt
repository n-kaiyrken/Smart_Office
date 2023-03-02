package com.example.smartoffice.presentation

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("channels/2037278/fields/2/last.json")
    fun getLastTemp(@Query("api_key") apiKey: String): Call<TempResponse>

    @GET("channels/2037278/fields/1/last.json")
    fun getLastHumidity(@Query("api_key") apiKey: String): Call<HumidityResponse>
}