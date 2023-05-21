package com.example.smartoffice.presentation.screens

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.smartoffice.R
import com.example.smartoffice.databinding.FragmentAttendanceBinding
import com.example.smartoffice.databinding.FragmentUnknownsBinding
import com.example.smartoffice.presentation.MainViewModel
import com.example.smartoffice.presentation.adapters.AttendanceAdapter
import com.example.smartoffice.presentation.adapters.UnknownIdsAdapter

class AttendanceFragment : Fragment() {

    private var _binding: FragmentAttendanceBinding? = null
    private val binding: FragmentAttendanceBinding
        get() = _binding ?: throw RuntimeException("FragmentAttendanceBinding is null")

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
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val adapter = AttendanceAdapter()
        binding.rvAttendance.adapter = adapter
        adapter.convertToTime = {viewModel.convertToTime(it)}
        viewModel.getEmployeeAttendance(employeeId)
        viewModel.employeeAttendanceLivaData.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                if (!it.get(0).equals(0L)) {
                    binding.textViewNoRecords.visibility = View.INVISIBLE
                }
            }
            binding.progressBar.visibility = View.GONE
            binding.rvAttendance.visibility = View.VISIBLE
            adapter.submitList(it)
        }
    }

    companion object {

        private const val EMPLOYEE_KEY = "employee"

        fun newInstance(employeeId: String) =
            AttendanceFragment().apply {
                arguments = Bundle().apply {
                    putString(EMPLOYEE_KEY, employeeId)
                }
            }
    }
}