package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.models.AirIndicators
import com.example.smartoffice.domain.Repository

class GetAirIndicatorsUseCase(private val repository: Repository) {

    suspend operator fun invoke(onDataLoaded: (AirIndicators) -> Unit) {
        repository.getAirIndicators(onDataLoaded)
    }
}