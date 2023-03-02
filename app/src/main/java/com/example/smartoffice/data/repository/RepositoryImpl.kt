package com.example.smartoffice.data.repository

import com.example.smartoffice.data.network.ApiFactory
import com.example.smartoffice.data.network.Mapper
import com.example.smartoffice.domain.HumidityResponse
import com.example.smartoffice.domain.Repository
import com.example.smartoffice.domain.TempResponse

class RepositoryImpl: Repository {

    private val apiService = ApiFactory.apiService

    private val apiKey = "JYY2LMKB25152SU1"

    private  val mapper = Mapper()

    override suspend fun getLastTemp(): TempResponse {
        val temp = mapper.mapDTOtoTempResponse(apiService.getLastTemp(apiKey))
        return temp
    }

    override suspend fun getLastHumidity(): HumidityResponse {
        val humidity = mapper.mapDTOtoHumidityResponse(apiService.getLastHumidity(apiKey))
        return humidity
    }
}