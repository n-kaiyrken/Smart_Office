package com.example.smartoffice.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.smartoffice.domain.models.Employee

class EmployeeDiffCallback: DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.phone == newItem.phone
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}