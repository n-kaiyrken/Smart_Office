package com.example.smartoffice.domain

interface Repository {

    suspend fun getLastTemp(): TempResponse

    suspend fun getLastHumidity(): HumidityResponse
}