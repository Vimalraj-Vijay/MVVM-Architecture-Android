package com.vimalvijay.dagger2.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vimalvijay.dagger2.main.model.Hero
import com.vimalvijay.dagger2.main.repository.MainRepository

class MainViewModel @ViewModelInject constructor(private var mainRepository: MainRepository) :
    ViewModel() {

    var heroList: MutableLiveData<Hero> = MutableLiveData()

    fun getHerosRepository(): MutableLiveData<Hero> {
        heroList = loadHerosList()
        return heroList
    }

    private fun loadHerosList(): MutableLiveData<Hero> {
        return mainRepository.getHeroListCall()
    }
}