package com.bhaktisudha.androidapplicationtask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModelItem

@Dao
interface ResultDao {

    @Insert
    suspend fun addResults(results:List<ResultModelItem>)

    @Query("SELECT * FROM results")
    suspend fun getResults() : List<ResultModelItem>
}