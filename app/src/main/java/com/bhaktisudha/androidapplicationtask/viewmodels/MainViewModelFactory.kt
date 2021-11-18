package com.bhaktisudha.androidapplicationtask.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bhaktisudha.androidapplicationtask.repository.ResultRepository

class MainViewModelFactory(private val repository: ResultRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}