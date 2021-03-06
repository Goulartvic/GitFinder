package com.example.gitfinder.base

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    var disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Log.d("ViewModel", "onCleared")
    }
}