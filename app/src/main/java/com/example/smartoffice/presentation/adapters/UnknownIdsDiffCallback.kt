package com.example.smartoffice.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.smartoffice.domain.models.UnknownId

class UnknownIdsDiffCallback: DiffUtil.ItemCallback<UnknownId>() {
    override fun areItemsTheSame(oldItem: UnknownId, newItem: UnknownId): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UnknownId, newItem: UnknownId): Boolean {
        return oldItem == newItem
    }
}