package com.example.mywebapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Author(
    val name: String,
    val date: String
)