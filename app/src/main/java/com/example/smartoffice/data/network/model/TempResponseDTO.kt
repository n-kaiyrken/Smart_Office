package com.example.smartoffice.data.network.model

import com.google.gson.annotations.SerializedName

data class TempResponseDTO(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    @SerializedName("field2") val field: String
)