package com.example.mywebapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Committer(
    val login: String,
    val url: String
)