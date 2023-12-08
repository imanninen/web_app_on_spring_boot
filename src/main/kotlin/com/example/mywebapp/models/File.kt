package com.example.mywebapp.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class File(
    @SerializedName("filename")
    val fileName: String,
    val status: String,
    val changes: Int
)
