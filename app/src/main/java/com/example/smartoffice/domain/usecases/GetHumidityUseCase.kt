package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository

class GetHumidityUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getLastHumidity()
}