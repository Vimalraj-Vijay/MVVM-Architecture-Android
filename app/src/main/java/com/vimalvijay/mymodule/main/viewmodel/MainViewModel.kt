package com.vimalvijay.mymodule.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vimalvijay.mymodule.main.model.Hero
import com.vimalvijay.mymodule.main.repository.MainRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel @ViewModelInject constructor(private var mainRepository: MainRepository) :
    ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    private val scope = CoroutineScope(coroutineContext)

    val heroListLiveData = MutableLiveData<MutableList<Hero.HeroItem>>()

    /**
     * Launch API using Coroutines
     */
    fun fetchHerosList() {
        scope.launch {
            heroListLiveData.postValue(mainRepository.getHerosList())
        }
    }

    fun cancelAllRequest() {
        coroutineContext.cancel()
    }
}
