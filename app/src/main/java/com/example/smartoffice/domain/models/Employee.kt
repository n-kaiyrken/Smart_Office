package com.example.smartoffice.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Employee(
    val name: String = "",
    val surname: String = "",
    val age: Int = 0,
    val phone: String = "",
    val id: String = ""
)
