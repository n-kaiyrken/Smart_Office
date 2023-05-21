package com.example.smartoffice.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.smartoffice.databinding.ItemUnknownBinding
import com.example.smartoffice.domain.models.UnknownId

class UnknownIdsAdapter: ListAdapter<UnknownId, UnknownIdsViewHolder>(
    UnknownIdsDiffCallback()
) {

    var onAddButtonClickListener: OnAddButtonClickListener? = null
    var convertToTime: (timeStamp: Long)-> String = {"null"}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnknownIdsViewHolder {
        val binding = ItemUnknownBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UnknownIdsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UnknownIdsViewHolder, position: Int) {
        val unknownId = getItem(position)
        with(holder.binding) {
            with(unknownId) {
                textviewTime.text = convertToTime(time)
                textviewId.text = id
                imageMenu.setOnClickListener {
                    onAddButtonClickListener?.onAddButtonClick(id)
                }
            }
        }
    }

    interface OnAddButtonClickListener {
        fun onAddButtonClick(unknownId: String)
    }
}