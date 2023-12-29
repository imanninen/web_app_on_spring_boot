package com.example.mywebapp.utils

import java.net.http.HttpResponse
import kotlin.IllegalStateException

class GitHubApiLimitException(message: String) : IllegalStateException(message)

class NullBodyException(message: String) : IllegalStateException(message)

class InvalidRepoOrUsernameException(message: String) : IllegalStateException(message)

internal fun throwCustomExceptions(response: HttpResponse<String?>): Nothing =
    when (response.statusCode()) {
        403 -> {
            println(response.body())
            throw GitHubApiLimitException("GitHub api reached limit of queries! Please try later!")
        }
        404 -> throw InvalidRepoOrUsernameException("Invalid repo name or username. Please try again!")
        else -> throw IllegalStateException("Code: ${response.statusCode()} There some error happened!")
    }