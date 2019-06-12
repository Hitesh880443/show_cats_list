package com.hitesh.showcatslist.catlist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class CatViewModelProvider(private val repo: CatRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CatViewModel(repo) as T
    }
}