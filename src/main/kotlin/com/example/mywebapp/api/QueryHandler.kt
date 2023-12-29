package com.example.mywebapp.api

import com.example.mywebapp.models.CommitKey
import com.example.mywebapp.models.CommitInfo
import com.example.mywebapp.utils.NullBodyException
import com.example.mywebapp.utils.throwCustomExceptions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object QueryHandler {
    fun getShaKey(username: String, repoName: String): List<CommitKey> {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.github.com/repos/$username/$repoName/commits"))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() != 200)
            throwCustomExceptions(response)

        val body = response.body() ?: throw NullBodyException("Null body!")
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
            if (response.statusCode() != 200)
                throwCustomExceptions(response)

            listOfCommitInfo.add(Gson().fromJson(response.body(), CommitInfo::class.java))
        }
        return listOfCommitInfo
    }
}