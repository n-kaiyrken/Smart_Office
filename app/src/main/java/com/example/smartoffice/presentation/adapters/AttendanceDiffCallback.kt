package com.example.smartoffice.presentation.adapters

import androidx.recyclerview.widget.DiffUtil

class AttendanceDiffCallback: DiffUtil.ItemCallback<Long>() {
    override fun areItemsTheSame(oldItem: Long, newItem: Long): Boolean {
        return (oldItem == newItem)
    }

    override fun areContentsTheSame(oldItem: Long, newItem: Long): Boolean {
        return (oldItem == newItem)
    }
}