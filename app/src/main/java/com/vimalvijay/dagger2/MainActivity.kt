package com.vimalvijay.dagger2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vimalvijay.dagger2.retrofit.ApiService
import com.vimalvijay.dagger2.model.Hero
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService

    lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getHeros()
    }

    private fun getHeros() {
        apiService.getHeroes().enqueue(object : Callback<Hero?> {
            override fun onFailure(call: Call<Hero?>, t: Throwable) {
                println("Error throw ${t.localizedMessage}")
            }

            override fun onResponse(call: Call<Hero?>, response: Response<Hero?>) {
                val heroList: Hero = response.body() as Hero
                generateView(heroList)

            }

        })

    }

    private fun generateView(hero: Hero) {
        val parentLayout = findViewById<LinearLayout>(R.id.llt_heros_list)
        val layoutInflater: LayoutInflater = layoutInflater


        parentLayout.removeAllViews()
        for (i in hero.indices) {
            view = layoutInflater.inflate(R.layout.heros_name_list, parentLayout, false)
            val tvHeroNames = view.findViewById<TextView>(R.id.tv_hero_names)
            tvHeroNames.text = hero[i].name
            parentLayout.addView(view)
        }
    }
}