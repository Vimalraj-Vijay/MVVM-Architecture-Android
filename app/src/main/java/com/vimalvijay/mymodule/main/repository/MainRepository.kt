package com.vimalvijay.mymodule.main.repository

import com.vimalvijay.mymodule.main.model.Hero
import com.vimalvijay.mymodule.network.ApiService
import com.vimalvijay.mymodule.network.BaseRepository
import javax.inject.Inject


class MainRepository @Inject constructor(var apiService: ApiService) : BaseRepository() {

    /**
     * Get Hero List from Api
     */
    suspend fun getHerosList(): MutableList<Hero.HeroItem>? {

        val heroResponse = safeApiRequest(
            call = { apiService.getHeroes().await() },
            errorMessage = "Error On" + Thread.currentThread().stackTrace[1].methodName
        )
        return heroResponse?.toMutableList()
    }
}