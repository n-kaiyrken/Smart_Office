package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository
import com.example.smartoffice.domain.models.Employee

class AddNewEmployeeUseCase(val repository: Repository) {

    suspend operator fun invoke(employee: Employee) {
        repository.addNewEmployee(employee)
    }
}