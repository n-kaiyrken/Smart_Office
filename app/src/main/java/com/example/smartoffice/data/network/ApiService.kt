package com.example.smartoffice.data.network

import com.example.smartoffice.data.network.model.HumidityResponseDTO
import com.example.smartoffice.data.network.model.TempResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("channels/2037278/fields/2/last.json")
    suspend fun getLastTemp(@Query(QUERY_PARAM_API_KEY) apiKey: String): TempResponseDTO

    @GET("channels/2037278/fields/1/last.json")
    suspend fun getLastHumidity(@Query(QUERY_PARAM_API_KEY) apiKey: String): HumidityResponseDTO

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
    }
}