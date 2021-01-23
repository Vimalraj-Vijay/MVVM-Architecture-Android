package com.vimalvijay.dagger2.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vimalvijay.dagger2.R
import com.vimalvijay.dagger2.main.model.Hero
import com.vimalvijay.dagger2.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var view: View

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Fetch
         */
        mainViewModel.fetchHerosList()
        /**
         * Observe Using Live data
         */
        mainViewModel.heroListLiveData.observe(this, Observer<MutableList<Hero.HeroItem>> { t -> generateView(t) })
    }

    /**
     * Set Views
     */
    private fun generateView(hero: MutableList<Hero.HeroItem>?) {
        val parentLayout = findViewById<LinearLayout>(R.id.llt_heros_list)
        val layoutInflater: LayoutInflater = layoutInflater

        parentLayout.removeAllViews()
        for (i in hero?.indices!!) {
            view = layoutInflater.inflate(R.layout.heros_name_list, parentLayout, false)
            val tvHeroNames = view.findViewById<TextView>(R.id.tv_hero_names)
            tvHeroNames.text = hero[i].name
            parentLayout.addView(view)
        }
    }
}