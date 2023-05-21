package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.domain.Repository

class GetEmployeesUseCase(val repository: Repository) {

    suspend operator fun invoke(onEmployeeLoaded: (List<Employee>) -> Unit) {
        repository.getEmployees(onEmployeeLoaded)
    }
}