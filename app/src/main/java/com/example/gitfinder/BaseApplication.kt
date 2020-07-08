package com.example.gitfinder

import android.app.Application
import com.example.gitfinder.api.GitHubApi
import com.example.gitfinder.utils.Constants.Companion.API_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class BaseApplication : Application() {

    companion object {
        lateinit var apiInstance: GitHubApi
    }

    override fun onCreate() {
        super.onCreate()
        apiInstance = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }
}