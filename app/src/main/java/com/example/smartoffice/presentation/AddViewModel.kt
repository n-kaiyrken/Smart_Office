package com.example.smartoffice.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartoffice.data.repository.RepositoryImpl
import com.example.smartoffice.domain.models.AirIndicators
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.domain.usecases.AddNewEmployeeUseCase
import kotlinx.coroutines.launch

class AddViewModel: ViewModel() {

    private val repository = RepositoryImpl()

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit> = _shouldCloseScreen

    private val addNewEmployeeUseCase = AddNewEmployeeUseCase(repository)

    fun addNewEmployee(
        inputname: String?,
        inputsurname: String?,
        inputage: String?,
        inputphone: String?,
        inputid: String?,
        showErrorToast: () -> Unit,
        showDoneToast: () -> Unit
    ) {
        val name = parseEmployeeString(inputname)
        val surname = parseEmployeeString(inputsurname)
        val age = parseEmployeeInt(inputage)
        val phone = parseEmployeeString(inputphone)
        val id = parseEmployeeString(inputid)
        val fiedsValid = validateInput(name, surname, age, phone)
        if (fiedsValid) {
            viewModelScope.launch {
                val employee = Employee(
                    name = name,
                    surname = surname,
                    age = age,
                    phone = phone,
                    id = id
                )
                addNewEmployeeUseCase.invoke(employee)
                showDoneToast()
                _shouldCloseScreen.value = Unit
            }
        } else {
            showErrorToast()
        }
    }

    private fun parseEmployeeString(inputText: String?): String {
        return inputText?.trim() ?: ""
    }

    private fun parseEmployeeInt(inputInt: String?): Int {
        return try {
            inputInt?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(
        name: String,
        surname: String,
        age: Int,
        phone: String,
    ): Boolean {
        var result = true
        if (name.isBlank() || surname.isBlank() || phone.isBlank()) {
            result = false
        }
        if (age <= 0) {
            result = false
        }
        return result
    }
}