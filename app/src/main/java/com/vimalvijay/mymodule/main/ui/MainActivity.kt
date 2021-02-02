package com.vimalvijay.mymodule.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vimalvijay.mymodule.R
import com.vimalvijay.mymodule.commonutils.BaseActivity
import com.vimalvijay.mymodule.commonutils.CustomProgressbar
import com.vimalvijay.mymodule.main.model.Hero
import com.vimalvijay.mymodule.main.viewmodel.MainViewModel
import com.vimalvijay.mymodule.network.responsehandler.Resource
import com.vimalvijay.mymodule.network.responsehandler.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var customProgressbar: CustomProgressbar

    lateinit var view: View

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Observe Using Live data
         */
        mainViewModel.heroListLiveData().observe(this, Observer {
            getResponse("Heros", it)
        })
    }

    /**
     * Set Views
     */
    private fun generateView(hero: MutableList<Hero.HeroItem>?) {
        customProgressbar.hideProgress()
        if (hero != null) {
            val parentLayout = findViewById<LinearLayout>(R.id.llt_heros_list)
            val layoutInflater: LayoutInflater = layoutInflater

            parentLayout.removeAllViews()
            for (i in hero.indices) {
                view = layoutInflater.inflate(R.layout.heros_name_list, parentLayout, false)
                val tvHeroNames = view.findViewById<TextView>(R.id.tv_hero_names)
                tvHeroNames.text = hero[i].name
                parentLayout.addView(view)
            }
        } else {
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        }
    }

    override fun <T> getResponse(isFromHeros: String, it: Resource<T>?) {
        it?.let { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    customProgressbar.showProgress(this)
                }
                Status.SUCCESS -> {
                    if (isFromHeros.equals("Heros")) {
                        generateView(it.data as MutableList<Hero.HeroItem>)
                    } else {
                        // get Another Response
                    }
                }
                Status.ERROR -> {
                    println("resources = ${resource.message}")
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                    customProgressbar.hideProgress()
                }

            }
        }
    }
}