package com.example.gitfinder.api

import retrofit2.http.GET
import retrofit2.http.Header
import java.util.*

interface GitHubApi {

    @GET("repositories")
    fun getRepositories(
        @Header("Accept") accept: String = "application/vnd.github.v3+json"
    ) : Observable
}