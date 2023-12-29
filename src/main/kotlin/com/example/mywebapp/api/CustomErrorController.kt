package com.example.mywebapp.api

import jakarta.servlet.RequestDispatcher
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class CustomErrorController : ErrorController {
    @RequestMapping("/error")
    fun handleError(request: HttpServletRequest, model: Model, response: HttpServletResponse): String {
        val status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE)
        if (status != null) {
            return when (Integer.parseInt(status.toString())) {
                HttpStatus.NOT_FOUND.value() -> "error/404"
                HttpStatus.BAD_REQUEST.value() -> "error/400"
                HttpStatus.INTERNAL_SERVER_ERROR.value() -> "error/500"
                else -> {
                    println(request)
                    "error/error"
                }
            }
        }
        return "error/error"
    }
}