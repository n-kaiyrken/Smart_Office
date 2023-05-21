package com.example.smartoffice.domain.usecases

import com.example.smartoffice.domain.Repository

class GetEmployeeAttendanceUseCase(val repository: Repository) {
    suspend operator fun invoke(id: String, onEmployeeAttendanceLoaded: (List<Long>) -> Unit) {
        repository.getEmployeeAttendance(id, onEmployeeAttendanceLoaded)
    }
}