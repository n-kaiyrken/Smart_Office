package com.example.smartoffice.data.network.thinkspeak.model

import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    @SerializedName("field4") val field: String
)