package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.repo

import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : List<GithubUser> {
        return repositories
    }
}