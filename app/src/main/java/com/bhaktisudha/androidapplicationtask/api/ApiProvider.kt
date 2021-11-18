package com.bhaktisudha.androidapplicationtask.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    fun getInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
   /* private fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .client(getHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun <s> createService(serviceClass: Class<s>?): s{
        return retrofit.create(serviceClass)
    }*/
}