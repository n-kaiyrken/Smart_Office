package com.example.smartoffice.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartoffice.data.repository.RepositoryImpl
import com.example.smartoffice.domain.models.AirIndicators
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.domain.models.UnknownId
import com.example.smartoffice.domain.usecases.*
import kotlinx.coroutines.launch
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.time.temporal.Temporal
import java.util.*
import kotlin.concurrent.fixedRateTimer

class MainViewModel : ViewModel() {

    private val repository = RepositoryImpl()

    private val _temperatureLivaData = MutableLiveData<String>()
    val temperatureLivaData: LiveData<String> = _temperatureLivaData

    private val _humidityLivaData = MutableLiveData<String>()
    val humidityLivaData: LiveData<String> = _humidityLivaData

    private val _gasLivaData = MutableLiveData<String>()
    val gasLivaData: LiveData<String> = _gasLivaData

    private val _employeesLivaData = MutableLiveData<List<Employee>>()
    val employeesLivaData: LiveData<List<Employee>> = _employeesLivaData

    private val _unknownIdsLivaData = MutableLiveData<List<UnknownId>>()
    val unknownIdsLivaData: LiveData<List<UnknownId>> = _unknownIdsLivaData

    private val _employeeAttendanceLivaData = MutableLiveData<List<Long>>()
    val employeeAttendanceLivaData: LiveData<List<Long>> = _employeeAttendanceLivaData

    private val getAirIndicatorsUseCase = GetAirIndicatorsUseCase(repository)
    private val getEmployeesUseCase = GetEmployeesUseCase(repository)
    private val getUnknownIdsUseCase = GetUnknownIdsUseCase(repository)
    private val getEmployeeAttendanceUseCase = GetEmployeeAttendanceUseCase(repository)
    private val deleteEmployeeUseCase = DeleteEmployeeUseCase(repository)
    private val openDoorUseCase = OpenDoorUseCase(repository)

    init {
        getAirIndicators()
        getEmployees()
        getUnknownIds()
    }

    private fun getUnknownIds() {
        viewModelScope.launch {
            getUnknownIdsUseCase.invoke {
                _unknownIdsLivaData.value = it
            }
        }
    }

    fun deleteEmployee(employeeId: String) {
        viewModelScope.launch {
            deleteEmployeeUseCase.invoke(employeeId)
        }
    }

    fun convertToTime(timestamp: Long): String {
        val timeZone = TimeZone.getTimeZone("Asia/Almaty")
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = timeZone
        val date = Date(timestamp)
        val formattedDate = sdf.format(date)

        return formattedDate
    }

    fun openDoor() {
        viewModelScope.launch {
            openDoorUseCase.invoke()
        }
    }

    fun getAirIndicators() {
        viewModelScope.launch {
            getAirIndicatorsUseCase.invoke {
                _temperatureLivaData.value = it.temp.toString() + " °C"
                _humidityLivaData.value = it.humdt.toString() + " %"
                _gasLivaData.value = it.gas.toString() + " PPM "
            }
        }
    }

    fun getEmployees() {
        viewModelScope.launch {
            getEmployeesUseCase.invoke {
                _employeesLivaData.value = it
            }
        }
    }

    fun getEmployeeAttendance(id: String) {
        viewModelScope.launch {
            getEmployeeAttendanceUseCase.invoke(id) {
                _employeeAttendanceLivaData.value = it
            }
        }
    }

    fun clearEmployeeAttendanceLivaData () {
        _employeeAttendanceLivaData.value = null
    }
}

