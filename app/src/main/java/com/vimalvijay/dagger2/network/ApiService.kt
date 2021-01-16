package com.vimalvijay.dagger2.network

import com.vimalvijay.dagger2.main.model.Hero
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("marvel")
    fun getHeroes(): Call<Hero>

    /*@GET("repos/{owner}/{name}")
    fun getRepo(
        @Path("owner") owner: String?,
        @Path("name") name: String?
    ): Single<Repo?>?*/

}