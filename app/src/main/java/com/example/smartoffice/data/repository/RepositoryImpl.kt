package com.example.smartoffice.data.repository

import com.example.smartoffice.data.network.FirebaseApi
import com.example.smartoffice.domain.models.AirIndicators
import com.example.smartoffice.domain.Repository
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.domain.models.UnknownId

class RepositoryImpl : Repository {

    private val firebaseApi = FirebaseApi()
    override suspend fun addNewEmployee(employee: Employee) {
        firebaseApi.addNewEmployee(employee)
    }

    override suspend fun deleteEmployee(employeeId: String) {
        firebaseApi.deleteEmployee(employeeId)
    }
    override suspend fun openDoor() {
        firebaseApi.openDoor()
    }

    override suspend fun getEmployees(onEmployeeLoaded: (List<Employee>) -> Unit) {
        firebaseApi.getEmployees(onEmployeeLoaded)
    }

    override suspend fun getAirIndicators(onDataLoaded: (AirIndicators) -> Unit) {
        firebaseApi.getAirIndicators(onDataLoaded)
    }

    override suspend fun getUnknownIds(onUnknownIdsLoaded: (List<UnknownId>) -> Unit) {
        firebaseApi.getUnknownIds(onUnknownIdsLoaded)
    }

    override suspend fun getEmployeeAttendance(id: String, onEmployeeAttendanceLoaded: (List<Long>) -> Unit) {
        firebaseApi.getEmployeeAttendance(id, onEmployeeAttendanceLoaded)
    }
}