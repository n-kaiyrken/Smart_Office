package com.example.smartoffice.presentation.screens

import android.content.res.AssetManager
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.smartoffice.R
import com.example.smartoffice.databinding.FragmentMainBinding
import com.example.smartoffice.presentation.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        observeLivedatas()

        binding.buttonShowUnknownsList.setOnClickListener {
            launchUnknownsFragment()
        }
        binding.buttonShowUsersList.setOnClickListener {
            launchEmployeesFragment()
        }
        val countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.buttonOpenDoor.isClickable = false
                binding.buttonOpenDoor.setTextColor(ContextCompat.getColor(requireContext(),R.color.green))
                binding.buttonOpenDoor.text = "Открыто - " + (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                binding.buttonOpenDoor.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                binding.buttonOpenDoor.text = requireContext().resources.getString(R.string.openDoor)
                binding.buttonOpenDoor.isClickable  = true
            }
        }
        binding.buttonOpenDoor.setOnClickListener {
            viewModel.openDoor()
            countDownTimer.start()
        }
        binding.animationView.playAnimation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.animationView.cancelAnimation()
        binding.animationView.clearAnimation()
    }

    fun observeLivedatas() {
        viewModel.temperatureLivaData.observe(viewLifecycleOwner) {
            binding.textViewTemp.text = it
        }
        viewModel.humidityLivaData.observe(viewLifecycleOwner) {
            binding.textViewHumidity.text = it
        }
        viewModel.gasLivaData.observe(viewLifecycleOwner) {
            binding.textViewGas.text = it
        }
    }

    fun launchEmployeesFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, EmployeesFragment.newInstance())
            .commit()
    }

    fun launchUnknownsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack("UnknownsFragment")
            .replace(R.id.fragment_container, UnknownsFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}