package com.example.smartoffice.data.network.thinkspeak

import com.example.smartoffice.data.network.thinkspeak.model.HumidityResponseDTO
import com.example.smartoffice.data.network.thinkspeak.model.NurlanResponseDTO
import com.example.smartoffice.data.network.thinkspeak.model.TempResponseDTO
import com.example.smartoffice.data.network.thinkspeak.model.UserResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("channels/2037278/fields/2/last.json")
    suspend fun getLastTemp(@Query(QUERY_PARAM_API_KEY) apiKey: String): TempResponseDTO

    @GET("channels/2037278/fields/1/last.json")
    suspend fun getLastHumidity(@Query(QUERY_PARAM_API_KEY) apiKey: String): HumidityResponseDTO

    @GET("channels/2037278/fields/3/last.json")
    suspend fun getLastNurlan(@Query(QUERY_PARAM_API_KEY) apiKey: String): NurlanResponseDTO

    @GET("channels/2037278/fields/4/last.json")
    suspend fun getLastUser(@Query(QUERY_PARAM_API_KEY) apiKey: String): UserResponseDTO

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
    }
}