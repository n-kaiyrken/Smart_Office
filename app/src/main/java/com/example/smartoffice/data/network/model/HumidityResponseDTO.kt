package com.example.smartoffice.data.network.model

import com.google.gson.annotations.SerializedName

data class HumidityResponseDTO(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    @SerializedName("field1") val field: String
)