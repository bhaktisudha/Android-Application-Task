package com.bhaktisudha.androidapplicationtask

import android.app.Application
import com.bhaktisudha.androidapplicationtask.api.ApiProvider
import com.bhaktisudha.androidapplicationtask.api.ApiService
import com.bhaktisudha.androidapplicationtask.db.ResultDatabase
import com.bhaktisudha.androidapplicationtask.repository.ResultRepository

class ResultApplication: Application() {
    lateinit var repository : ResultRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val apiService = ApiProvider.getInstance().create(ApiService::class.java)
        val database = ResultDatabase.getDatabase(applicationContext)
        repository = ResultRepository(apiService,database,applicationContext)
    }
}