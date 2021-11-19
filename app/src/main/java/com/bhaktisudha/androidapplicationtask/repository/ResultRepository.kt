package com.bhaktisudha.androidapplicationtask.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhaktisudha.androidapplicationtask.api.ApiService
import com.bhaktisudha.androidapplicationtask.db.ResultDatabase
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModel
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModelItem
import com.bhaktisudha.androidapplicationtask.utils.NetworkUtils

class ResultRepository(
    private val apiService: ApiService,
    private val resultDatabase: ResultDatabase,
    private val applicationContext: Context
) {
    private val resultLiveData = MutableLiveData<ResultModel>()
    val results: LiveData<ResultModel>
        get() = resultLiveData


    suspend fun getResult(accountId: String) {
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = apiService.getResult(accountId)
            if (result?.body() != null) {
                resultDatabase.resultDao().deleteAll()
                resultDatabase.resultDao().addResults(result.body()!!)
                Log.d("Room", resultDatabase.resultDao().getResults().toString())
                Log.d("Room Data", resultDatabase.resultDao().getResults().size.toString())

                resultLiveData.postValue(result.body())

            }
        } else {
            val response = resultDatabase.resultDao().getResults().size.toInt()
            val resultModel = ResultModel()
            for (i in 0 until response) {
                //resultModel.add(resultDatabase.resultDao().getResults().get(i))
                resultModel += resultDatabase.resultDao().getResults().get(i)

            }
            Log.d("Room Not", resultDatabase.resultDao().getResults().size.toString())
            Log.d("Room String", resultDatabase.resultDao().getResults().toString())
            Log.d("Room Result model", resultModel.size.toString())

            resultLiveData.postValue(resultModel)


        }
        /*val result = apiService.getResult(accountId)
        if(result?.body() != null){
            resultDatabase.resultDao().addResults(result.body()!!)
            resultDatabase.resultDao().getResults()
            resultcoins = resultDatabase.resultDao().getAll();
            Log.d("Api",result.body().toString())
            Log.d("Room",resultDatabase.resultDao().getResults().toString())
            Log.d("Room Size", resultDatabase.resultDao().getResults().toString())
            val users: List<ResultModelItem> = resultDatabase.resultDao().getResults()

            Log.d("coin Size", resultDatabase.resultDao().getAll().toString())

            resultLiveData.postValue(result.body())

        }*/
    }

    suspend fun delete(results: ResultModelItem){
        resultDatabase.resultDao().deleteResponseItem(results)
    }

    suspend fun update(results: ResultModelItem){
        resultDatabase.resultDao().update(results)
    }
}