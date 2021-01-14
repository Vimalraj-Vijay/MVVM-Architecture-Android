package com.vimalvijay.dagger2.retrofit

import com.vimalvijay.dagger2.model.Hero
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("marvel")
    fun getHeroes(): Call<Hero>

}