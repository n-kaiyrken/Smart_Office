package com.example.smartoffice.presentation.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.smartoffice.R
import com.example.smartoffice.databinding.FragmentEmployeesBinding
import com.example.smartoffice.databinding.FragmentMainBinding
import com.example.smartoffice.domain.models.Employee
import com.example.smartoffice.presentation.MainViewModel
import com.example.smartoffice.presentation.adapters.EmployeesAdapter

class EmployeesFragment : Fragment() {

    private var _binding: FragmentEmployeesBinding? = null
    private val binding: FragmentEmployeesBinding
        get() = _binding ?: throw RuntimeException("FragmentEmployeesBinding is null")

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val adapter = EmployeesAdapter()
        binding.rvEmployeesList.adapter = adapter
        viewModel.employeesLivaData.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                if (!it.get(0).equals(0L)) {
                    binding.textViewNoRecords.visibility = View.INVISIBLE
                }
            }
            adapter.submitList(it)
        }
        adapter.onEmployeeClickListener = object : EmployeesAdapter.OnEmployeeClickListener {
            override fun onEmployeeClick(employeeId: String) {
                launchDetailsFragment(employeeId)
            }
        }
        adapter.onMoreClickListener = object : EmployeesAdapter.OnMoreClickListener {
            override fun onMoreClick(employeeId: String, view: View) {
                showPopupMenu(view, employeeId)
            }
        }
    }

    private fun showPopupMenu(view: View, employeeId: String) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_details -> {
                    launchDetailsFragment(employeeId)
                    true
                }
                R.id.action_history -> {
                    viewModel.clearEmployeeAttendanceLivaData()
                    launchAttendanceFragment(employeeId)
                    true
                }
                R.id.action_delete -> {
                    showDeleteDialog(requireContext(), employeeId)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    fun showDeleteDialog(context: Context, employeeId: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.resources.getString(R.string.deleteDialogTitle))
        builder.setMessage(context.resources.getString(R.string.deleteDialogDescription))
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            viewModel.deleteEmployee(employeeId)
        }
        builder.setNegativeButton("Нет") { _, _ -> }
        builder.show()
    }

    private fun launchDetailsFragment(employeeId: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, DetailsFragment.newInstance(employeeId))
            .commit()
    }

    private fun launchAttendanceFragment(employeeId: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container, AttendanceFragment.newInstance(employeeId))
            .commit()
    }

    companion object {
        fun newInstance() = EmployeesFragment()
    }
}