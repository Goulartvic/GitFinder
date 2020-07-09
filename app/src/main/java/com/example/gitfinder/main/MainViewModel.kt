package com.example.gitfinder.main

import androidx.lifecycle.MutableLiveData
import com.example.gitfinder.BaseApplication
import com.example.gitfinder.base.BaseViewModel
import com.example.gitfinder.models.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel : BaseViewModel() {

    private var apiInstance = BaseApplication.apiInstance

    var repositories = MutableLiveData<List<Repository>>()
    var error = MutableLiveData<Boolean>()

    fun getRepositories() {
        apiInstance.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    repositories.postValue(it)
                }
            }, {
                error.postValue(true)
            })
    }

    fun searchRepositories(repoName: String) {
        apiInstance.searchRepositories(repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let {
                    repositories.postValue(it.items)
                }
            }, {
                error.postValue(true)
            })
    }
}