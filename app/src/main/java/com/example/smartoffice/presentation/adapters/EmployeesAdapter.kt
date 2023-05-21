package com.example.smartoffice.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.smartoffice.databinding.ItemEmployeesBinding
import com.example.smartoffice.domain.models.Employee

class EmployeesAdapter: ListAdapter<Employee, EmployeesViewHolder>(
    EmployeeDiffCallback()
) {

    var onEmployeeClickListener: OnEmployeeClickListener? = null
    var onMoreClickListener: OnMoreClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val binding = ItemEmployeesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EmployeesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        val employee = getItem(position)
        with(holder.binding) {
            with(employee) {
                nameTextview.text = "$name $surname"
                idTextview.text = id
                imageMenu.setOnClickListener {
                    onMoreClickListener?.onMoreClick(employee.id, it)
                }

                //Picasso.get().load(imageUrl).into(ivLogoCoin)
                root.setOnClickListener {
                    onEmployeeClickListener?.onEmployeeClick(employee.id)
                }
            }
        }
    }

    interface OnEmployeeClickListener {
        fun onEmployeeClick(employeeId: String)
    }

    interface OnMoreClickListener {
        fun onMoreClick(employeeId: String, view: View)
    }
}