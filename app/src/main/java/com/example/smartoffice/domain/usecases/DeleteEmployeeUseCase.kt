package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository

class DeleteEmployeeUseCase(val repository: Repository) {
    suspend operator fun invoke(id: String) {
        repository.deleteEmployee(id)
    }
}