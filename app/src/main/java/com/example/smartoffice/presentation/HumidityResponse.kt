package com.example.smartoffice.presentation

import com.google.gson.annotations.SerializedName

data class HumidityResponse(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    @SerializedName("field1") val field1: String
)
