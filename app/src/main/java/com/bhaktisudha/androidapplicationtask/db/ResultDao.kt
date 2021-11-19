package com.bhaktisudha.androidapplicationtask.db

import androidx.room.*
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModelItem


@Dao
interface ResultDao {

    @Insert
    suspend fun addResults(results:List<ResultModelItem>)

    @Query("SELECT * FROM results")
    suspend fun getResults() : List<ResultModelItem>

    @Query("DELETE FROM results")
    fun deleteAll()

    @Delete
    suspend fun deleteResponseItem(results: ResultModelItem)

    @Update
    fun update(results: ResultModelItem)

}