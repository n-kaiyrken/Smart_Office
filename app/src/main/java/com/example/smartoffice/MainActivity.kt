package com.example.smartoffice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.smartoffice.databinding.ActivityMainBinding
import com.example.smartoffice.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.temperatureLivaData.observe(this) {
            binding.textViewTemp.text = it
        }
        viewModel.humidityLivaData.observe(this) {
            binding.textViewHumidity.text = it
        }
        binding.button.setOnClickListener {
            Log.d("MyResponse", "${Thread.currentThread()}")
            viewModel.getTemp()
        }
    }
}