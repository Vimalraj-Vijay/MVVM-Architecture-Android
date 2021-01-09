package com.vimalvijay.dagger2.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    private var instance: RetrofitClient? = null
    private var myApi: ApiService? = null

    private fun RetrofitClient(): RetrofitClient? {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        myApi = retrofit.create(ApiService::class.java)
        return this
    }

    @Synchronized
    fun getInstance(): RetrofitClient? {
        if (instance == null) {
            instance = RetrofitClient()
        }
        return instance
    }

    fun getMyApi(): ApiService? {
        return myApi
    }
}