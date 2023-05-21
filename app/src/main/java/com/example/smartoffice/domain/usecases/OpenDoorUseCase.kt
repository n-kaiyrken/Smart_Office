package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository

class OpenDoorUseCase(val repository: Repository) {
    suspend operator fun invoke() {
        repository.openDoor()
    }
}