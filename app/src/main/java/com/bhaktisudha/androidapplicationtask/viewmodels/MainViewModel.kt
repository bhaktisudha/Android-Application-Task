package com.bhaktisudha.androidapplicationtask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhaktisudha.androidapplicationtask.repository.ResultRepository
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ResultRepository): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getResult("fdd7eff6-3b33-4b12-81d2-fc9d18b2099e")

        }
    }

    val results : LiveData<ResultModel>
    get() = repository.results
}