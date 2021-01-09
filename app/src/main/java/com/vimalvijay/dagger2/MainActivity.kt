package com.vimalvijay.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vimalvijay.dagger2.retrofit.Hero
import com.vimalvijay.dagger2.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHeros()
    }

    private fun getHeros() {
        val call: Call<Hero>? = RetrofitClient().getInstance()?.getMyApi()?.getHeroes()
        call?.enqueue(object : Callback<Hero?> {
            override fun onFailure(call: Call<Hero?>, t: Throwable) {
                println("Error throw ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Hero?>, response: Response<Hero?>) {
                val heroList: Hero = response.body() as Hero
                for (i in heroList.indices) {
                    println("Hero list ${heroList.get(i).name}")
                }

            }

        })

    }
}


