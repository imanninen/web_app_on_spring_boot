package com.example.mywebapp.api

import com.example.mywebapp.models.RepoInfo
import com.example.mywebapp.utils.GitHubApiLimitException
import com.example.mywebapp.utils.InvalidRepoOrUsernameException
import com.example.mywebapp.utils.NullBodyException
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
        try {
            val listOfKeys = QueryHandler.getShaKey(username, repoName)
            val listOfCommitsInfo = QueryHandler.getMainResponse(username, repoName, listOfKeys)
            map.addAttribute("listOfCommits", listOfCommitsInfo)
            return "commitsInfo"
        } catch (exception: IllegalStateException) {
            return when (exception) {
                is GitHubApiLimitException -> {
                    map.addAttribute("errorMessage", exception.message)
                    "commitsInfo"
                }

                is NullBodyException -> {
                    map.addAttribute("errorMessage", exception.message)
                    "commitsInfo"
                }

                is InvalidRepoOrUsernameException -> {
                    map.addAttribute("errorMessage", exception.message)
                    "commitsInfo"
                }

                else -> "redirect:/"
            }

        }
    }

    @PostMapping("/")
    fun processUsernameAndRepoName(model: Model, repoInfo: RepoInfo): String =
        "redirect:/api/get_commits/${repoInfo.username}/${repoInfo.repoName}"

}