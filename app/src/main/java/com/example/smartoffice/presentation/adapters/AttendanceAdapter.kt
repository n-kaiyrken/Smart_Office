package com.example.smartoffice.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.smartoffice.databinding.ItemEmployeesBinding
import com.example.smartoffice.databinding.ItemHistoryBinding
import com.example.smartoffice.domain.models.Employee

class AttendanceAdapter: ListAdapter<Long, AttendanceViewHolder>(
    AttendanceDiffCallback()
) {
    var convertToTime: (timeStamp: Long)-> String = {"null"}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val binding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AttendanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val time = getItem(position)
        holder.binding.textviewTime.text = convertToTime(time)
    }
}