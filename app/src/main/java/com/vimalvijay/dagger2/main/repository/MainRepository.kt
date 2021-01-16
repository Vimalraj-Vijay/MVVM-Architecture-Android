package com.vimalvijay.dagger2.main.repository

import androidx.lifecycle.MutableLiveData
import com.vimalvijay.dagger2.main.model.Hero
import com.vimalvijay.dagger2.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(var apiService: ApiService) {

    val heroList: MutableLiveData<Hero> = MutableLiveData()


    fun getHeroListCall(): MutableLiveData<Hero> {
        apiService.getHeroes().enqueue(object : Callback<Hero?> {
            override fun onFailure(call: Call<Hero?>, t: Throwable) {
                println("Error throw ${t.localizedMessage}")
                heroList.postValue(null)
            }

            override fun onResponse(call: Call<Hero?>, response: Response<Hero?>) {
                val callheroList: Hero = response.body() as Hero
                heroList.value = response.body()
            }

        })

        return heroList
    }
}