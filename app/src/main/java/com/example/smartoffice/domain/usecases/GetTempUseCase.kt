package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository

class GetTempUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.getLastTemp()
}