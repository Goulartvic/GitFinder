package com.example.gitfinder.api

import com.example.gitfinder.models.Repositories
import com.example.gitfinder.models.Repository
import com.example.gitfinder.utils.Constants.Companion.ACCEPT
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GitHubApi {

    @GET("/repositories")
    fun getRepositories(
        @Header("Accept") accept: String = ACCEPT
    ) : Observable<List<Repository>>

    @GET("/search/repositories")
    fun searchRepositories(
        @Query("q") repoName: String,
        @Header("Accept") accept: String = ACCEPT
    ) : Observable<Repositories>
}