package com.example.mywebapp.api

import com.example.mywebapp.models.CommitKey
import com.example.mywebapp.models.CommitInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object QueryHandler {
    private fun String.utf8(): String = URLEncoder.encode(this, "UTF-8")

    fun getShaKey(username: String, repoName: String): List<CommitKey> {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.github.com/repos/$username/$repoName/commits"))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200) {
            throw IllegalStateException("You have a error in a query or github decided to sleep a bit:)")
        }
        val body = response.body() ?: throw IllegalStateException("Null body???")
        if (body == "[]") {
            println("Invalid repo name or username. Please try again!")
        }
        val typeToken = object : TypeToken<List<CommitKey>>() {}.type
        return Gson().fromJson(body, typeToken)
    }

    fun getMainResponse(username: String, repoName: String, listOfShaKeys: List<CommitKey>): List<CommitInfo> {
        val client = HttpClient.newBuilder().build()
        val listOfCommitInfo = mutableListOf<CommitInfo>()
        listOfShaKeys.forEach {
            val newUrl = "https://api.github.com/repos/$username/$repoName/commits/${it.sha}"
            val request = HttpRequest.newBuilder()
                .uri(URI.create(newUrl))
                .build()
            val response = client.send(request, HttpResponse.BodyHandlers.ofString())
            if (response.statusCode() != 200) {
                throw IllegalStateException("Invalid query!")
            }
            listOfCommitInfo.add(Gson().fromJson(response.body(), CommitInfo::class.java))
        }
        return listOfCommitInfo
    }
}