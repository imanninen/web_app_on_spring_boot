package com.example.mywebapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyWebAppApplication

fun main(args: Array<String>) {
    runApplication<MyWebAppApplication>(*args)
}
