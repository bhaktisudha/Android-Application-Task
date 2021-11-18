package com.bhaktisudha.androidapplicationtask.api

import com.bhaktisudha.androidapplicationtask.resultModel.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    companion object{
        val BASE_URL : String = "https://sandbox-sps.secur.space/public/api/"
    }
    @Headers(
        "Authorization:RaczV/TvKB92Xz4eDpgxCacfFRTP8mXJnnpteiUmkymI5vxt9iPTgoQEH6ZgAwDXylGyDHgkH0cN5MnVgKrnpg==",
        "User-Agent:application/json"
    )
    @GET("driver")
    suspend fun getResult(@Query("accountId") query : String?) : Response<ResultModel>
}