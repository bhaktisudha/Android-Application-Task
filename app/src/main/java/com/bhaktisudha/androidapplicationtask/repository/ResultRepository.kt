package com.bhaktisudha.androidapplicationtask.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhaktisudha.androidapplicationtask.api.ApiService
import com.bhaktisudha.androidapplicationtask.db.ResultDatabase
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModel

class ResultRepository(private val apiService: ApiService,private val resultDatabase: ResultDatabase) {
     private val resultLiveData = MutableLiveData<ResultModel>()
     val results : LiveData<ResultModel>
     get() = resultLiveData
    suspend fun getResult(accountId : String){
        val result = apiService.getResult(accountId)
        if(result?.body() != null && resultDatabase.resultDao().getResults().size != 0){
            resultDatabase.resultDao().addResults(result.body()!!)
            resultDatabase.resultDao().getResults()
            Log.d("Api",result.body().toString())
            Log.d("Room",resultDatabase.resultDao().getResults().toString())
            resultLiveData.postValue(result.body())

        }
    }
}