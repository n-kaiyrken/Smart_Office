package com.example.smartoffice.domain

import com.example.smartoffice.domain.models.AirIndicators
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.domain.models.UnknownId

interface Repository {

    suspend fun openDoor()
    suspend fun deleteEmployee(employeeId: String)
    suspend fun addNewEmployee(employee: Employee)
    suspend fun getEmployees(onEmployeeLoaded: (List<Employee>) -> Unit)
    suspend fun getAirIndicators(onDataLoaded: (AirIndicators) -> Unit)
    suspend fun getUnknownIds(onUnknownIdsLoaded: (List<UnknownId>) -> Unit)
    suspend fun getEmployeeAttendance(id: String, onEmployeeAttendanceLoaded: (List<Long>) -> Unit)
}