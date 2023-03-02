package com.example.smartoffice.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    private val _temperatureLivaData = MutableLiveData<String>()
    val temperatureLivaData: LiveData<String> = _temperatureLivaData

    private val _humidityLivaData = MutableLiveData<String>()
    val humidityLivaData: LiveData<String> = _humidityLivaData

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thingspeak.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    val apiKey = "JYY2LMKB25152SU1"


    fun getTemp() {
        apiService.getLastTemp(apiKey).enqueue(
            object : Callback<TempResponse> {
                override fun onResponse(
                    call: Call<TempResponse>,
                    response: Response<TempResponse>
                ) {
                    if (response.isSuccessful) {
                        val temperature = response.body()
                        Log.d("MyResponse", "Temperature $temperature")
                        if (temperature != null) {
                            _temperatureLivaData.value = temperature.field2
                        }
                    } else {
                        Log.d("MyResponse", "Error")
                    }
                    Log.d("MyResponse", "${Thread.currentThread()}")
                }

                override fun onFailure(call: Call<TempResponse>, t: Throwable) {
                    // Handle network error here
                    Log.d("MyResponse", "onFailure  {${t.printStackTrace()}}")
                    t.printStackTrace()
                }
            })

        apiService.getLastHumidity(apiKey).enqueue(
            object : Callback<HumidityResponse> {
                override fun onResponse(
                    call: Call<HumidityResponse>,
                    response: Response<HumidityResponse>
                ) {
                    val humidity = response.body()
                    Log.d("MyResponse", "HUMIDITY $humidity")
                    _humidityLivaData.value = humidity?.field1
                }

                override fun onFailure(call: Call<HumidityResponse>, t: Throwable) {
                    Log.d("MyResponse", "onFailure")
                }

            }
        )
    }
}

