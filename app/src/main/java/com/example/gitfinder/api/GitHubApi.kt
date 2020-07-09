package com.example.gitfinder.api

import com.example.gitfinder.models.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface GitHubApi {

    @GET("/repositories")
    fun getRepositories(
        @Header("Accept") accept: String = "application/vnd.github.v3+json"
    ) : Observable<List<Repository>>
}