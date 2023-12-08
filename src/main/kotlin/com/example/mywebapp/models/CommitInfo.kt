package com.example.mywebapp.models

import kotlinx.serialization.Serializable

@Serializable
data class CommitInfo(
    val commit: Commit,
    val committer: Committer,
    val files: List<File>
)