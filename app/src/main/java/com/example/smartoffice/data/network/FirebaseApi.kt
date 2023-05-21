package com.example.smartoffice.data.network

import android.util.Log
import com.example.smartoffice.domain.models.AirIndicators
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.domain.models.UnknownId
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FirebaseApi {

    private var databaseRef: DatabaseReference = Firebase.database.reference

    val airIndicatorsRef = databaseRef.child("test")
    val employeesRef = databaseRef.child("employee/info")
    val unknownsIdsRef = databaseRef.child("employee/unknownIds")
    val attendanceRef = databaseRef.child("employee/attendance")
    val doorStatusRef = databaseRef.child("employee/doorStatus")

    fun addNewEmployee(employee: Employee) {
        employeesRef.child(employee.id).setValue(employee)
        deleteUnknownId(employee.id)
    }

    private fun deleteUnknownId(id: String) {
        unknownsIdsRef.child(id).removeValue()
    }

    fun deleteEmployee(id: String) {
        employeesRef.child(id).removeValue()
        attendanceRef.child(id).removeValue()
    }

    fun openDoor() {
        val doorStatusDataSnapshot = doorStatusRef.get().addOnSuccessListener {
            val doorStatus = it.getValue<Int>()
            if (doorStatus == 0) {
                doorStatusRef.setValue(1)
            } else if (doorStatus == 1) {
                doorStatusRef.setValue(0)
            }
            Log.d("Check door status", "$doorStatus")
        }
    }

    fun getAirIndicators(onDataLoaded: (AirIndicators) -> Unit) {
        val airIndicatorsListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val airIndicators = dataSnapshot.getValue<AirIndicators>()
                    ?: throw RuntimeException("dataSnapshot.getValue<AirIndicators> is null")
                onDataLoaded(airIndicators)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Check", "readAirIndicators():onCancelled", databaseError.toException())
            }
        }
        airIndicatorsRef.addValueEventListener(airIndicatorsListener)
    }

    fun getEmployees(onEmployeeLoaded: (List<Employee>) -> Unit) {
        val employeeListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val employeeList = mutableListOf<Employee>()
                for (employeeSnapshot in dataSnapshot.children) {
                    val employee = employeeSnapshot.getValue<Employee>()
                        ?: throw RuntimeException("employee is null")
                    val firstName = employee.name
                    val lastName = employee.surname
                    val age = employee.age
                    val phone = employee.phone
                    val id = employeeSnapshot.key ?: "null"
                    employeeList.add(employee)
                    Log.d("Checking firebase", "Employee $id: $firstName $lastName $age $phone")
                }
                onEmployeeLoaded(employeeList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error reading employees: " + databaseError.message)
            }
        }
        employeesRef.addValueEventListener(employeeListener)
    }

    fun getUnknownIds(onUnknownIdsLoaded: (List<UnknownId>) -> Unit) {
        val unknownIdsListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val unknowIdsList = mutableListOf<UnknownId>()
                for (unknownIdSnapshot in dataSnapshot.children) {
                    val unknownId = unknownIdSnapshot.key ?: "null id"
                    val time = unknownIdSnapshot.value
                    unknowIdsList.add(UnknownId(unknownId, time as Long))
                }
                onUnknownIdsLoaded(unknowIdsList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error reading unknownIds: " + databaseError.message)
            }
        }
        unknownsIdsRef.addValueEventListener(unknownIdsListener)
    }

    fun getEmployeeAttendance(id: String, onEmployeeAttendanceLoaded: (List<Long>) -> Unit) {

        val employeeAttendanceRef = attendanceRef.child(id)

        val employeeAttendanceListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val employeeAttendanceList = mutableListOf<Long>()
                for (employeeAttendanceSnapshot in dataSnapshot.children) {
                    val time = employeeAttendanceSnapshot.value ?: 0
                    employeeAttendanceList.add(time as Long)
                }
                onEmployeeAttendanceLoaded(employeeAttendanceList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("Error reading employeeAttendance: " + databaseError.message)
            }
        }
        employeeAttendanceRef.addValueEventListener(employeeAttendanceListener)
    }
}