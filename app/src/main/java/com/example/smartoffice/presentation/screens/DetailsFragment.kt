package com.example.smartoffice.presentation.screens

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartoffice.R
import com.example.smartoffice.databinding.FragmentDetailsBinding
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.presentation.MainViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding is null")

    private lateinit var viewModel: MainViewModel

    private var employeeId: String = "null"

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        if (!args.containsKey(EMPLOYEE_KEY)) {
            throw RuntimeException("Param employee id is absent!")
        }
        employeeId = args.getString(EMPLOYEE_KEY, "null")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val employee = viewModel.employeesLivaData.value?.find {
            it.id == employeeId
        }
        val fullname = "${employee?.name} ${employee?.surname}"
        with(binding) {
            textviewAge.text = employee?.age.toString()
            textviewCardId.text = employee?.id
            textviewPhone.text = employee?.phone
            fullNameTextview.text = fullname
            //Picasso.get().load(it.imageUrl).into(ivLogoCoin)
        }
    }

    companion object {

        private const val EMPLOYEE_KEY = "employee"

        fun newInstance(employeeId: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EMPLOYEE_KEY, employeeId)
                }
            }
    }
}