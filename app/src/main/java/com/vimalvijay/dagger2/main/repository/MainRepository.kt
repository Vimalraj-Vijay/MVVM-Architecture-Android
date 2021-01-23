package com.vimalvijay.dagger2.main.repository

import com.vimalvijay.dagger2.main.model.Hero
import com.vimalvijay.dagger2.network.ApiService
import com.vimalvijay.dagger2.network.BaseRepository
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