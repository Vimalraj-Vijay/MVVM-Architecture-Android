package com.vimalvijay.mymodule.network

import com.vimalvijay.mymodule.main.model.Hero
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("marvel")
    fun getHeroes(): Deferred<Response<Hero>>

}