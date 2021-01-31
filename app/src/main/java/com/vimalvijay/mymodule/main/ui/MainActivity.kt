package com.vimalvijay.mymodule.main.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vimalvijay.mymodule.R
import com.vimalvijay.mymodule.commonutils.CustomProgressbar
import com.vimalvijay.mymodule.main.model.Hero
import com.vimalvijay.mymodule.main.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var customProgressbar: CustomProgressbar

    lateinit var view: View

    private val mainViewModel: MainViewModel by viewModels()
    var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = ProgressDialog(this)
        /**
         * Fetch
         */

        /*progress!!.setTitle("Loading")
        progress!!.setMessage("Wait while loading...")
        progress!!.setCancelable(false) // disable dismiss by tapping outside of the dialog

        progress!!.show()*/
        customProgressbar.showProgress(this)
        mainViewModel.fetchHerosList()
        /**
         * Observe Using Live data
         */
        mainViewModel.heroListLiveData.observe(
            this,
            Observer<MutableList<Hero.HeroItem>> { t -> generateView(t) })
    }

    /**
     * Set Views
     */
    private fun generateView(hero: MutableList<Hero.HeroItem>?) {
        customProgressbar.hideProgress()
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