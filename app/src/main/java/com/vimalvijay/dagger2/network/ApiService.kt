package com.vimalvijay.dagger2.network

import com.vimalvijay.dagger2.main.model.Hero
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("marvel")
   suspend fun getHeroes(): Response<Hero>

}