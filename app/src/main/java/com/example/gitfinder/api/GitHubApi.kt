package com.example.gitfinder.api

import com.example.gitfinder.models.Repository
import com.example.gitfinder.utils.Constants.Companion.ACCEPT
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface GitHubApi {

    @GET("/repositories")
    fun getRepositories(
        @Header("Accept") accept: String = ACCEPT
    ) : Observable<List<Repository>>
}