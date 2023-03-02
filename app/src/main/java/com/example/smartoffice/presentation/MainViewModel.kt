package com.example.smartoffice.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartoffice.data.repository.RepositoryImpl
import com.example.smartoffice.domain.usecases.GetHumidityUseCase
import com.example.smartoffice.domain.usecases.GetTempUseCase
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = RepositoryImpl()

    private val _temperatureLivaData = MutableLiveData<String>()
    val temperatureLivaData: LiveData<String> = _temperatureLivaData

    private val _humidityLivaData = MutableLiveData<String>()
    val humidityLivaData: LiveData<String> = _humidityLivaData

    private val getTempUseCase = GetTempUseCase(repository)
    private val getHumidityUseCase = GetHumidityUseCase(repository)



    fun updateData() {
        viewModelScope.launch {
            _temperatureLivaData.value = getTempUseCase.invoke().field
            _humidityLivaData.value = getHumidityUseCase.invoke().field
        }
    }
}

