package com.example.smartoffice.presentation

import com.google.gson.annotations.SerializedName

data class TempResponse(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("entry_id") val entryId: Int,
    @SerializedName("field2") val field2: String
)



