package com.example.smartoffice.presentation

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    @SerializedName("field") val field: String
)