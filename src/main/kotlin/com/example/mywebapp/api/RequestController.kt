package com.example.mywebapp.api

import com.example.mywebapp.models.RepoInfo
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/get_commits/")
class RequestController {
    @RequestMapping("/{username}/{repoName}")
    fun getCommitsInfo(@PathVariable repoName: String, @PathVariable username: String, map: ModelMap): String {
        val listOfKeys = QueryHandler.getShaKey(username, repoName)
        val listOfCommitsInfo = QueryHandler.getMainResponse(username, repoName, listOfKeys)
        map.addAttribute("listOfCommits", listOfCommitsInfo)
        return "commitsInfo"
    }

    @PostMapping("/")
    fun processUsernameAndRepoName(model: Model, repoInfo: RepoInfo): String =
        "redirect:/api/get_commits/${repoInfo.username}/${repoInfo.repoName}"

}