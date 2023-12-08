package com.example.mywebapp.api

import com.example.mywebapp.models.RepoInfo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class FormController {
    @GetMapping("/")
    fun processUsernameAndRepoName(model: Model): String {
        val rr = RepoInfo()
        rr.username = ""
        rr.repoName = ""
        model.addAttribute("repoInfo", rr)
        return "index"
    }
}