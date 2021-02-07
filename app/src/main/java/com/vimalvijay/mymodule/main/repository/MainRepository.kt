package com.vimalvijay.mymodule.main.repository

import com.vimalvijay.mymodule.commonutils.BaseRepository
import com.vimalvijay.mymodule.main.model.Hero
import com.vimalvijay.mymodule.network.api.ApiService
import javax.inject.Inject


class MainRepository @Inject constructor(var apiService: ApiService) : BaseRepository() {

    /**
     * Get Hero List from Api
     */
    suspend fun getHerosList(): MutableList<Hero.HeroItem>? {
        val heroResponse = safeApiRequest(
            call = { apiService.getHeroes().await() })
        return heroResponse?.toMutableList()
    }
}