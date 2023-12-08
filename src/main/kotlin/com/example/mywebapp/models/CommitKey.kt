package com.example.mywebapp.models

import kotlinx.serialization.Serializable

@Serializable
data class CommitKey(
    val sha: String
)
