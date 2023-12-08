package com.example.mywebapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    val author: Author,
    val message: String
)
