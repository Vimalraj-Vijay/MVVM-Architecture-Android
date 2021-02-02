package com.vimalvijay.mymodule.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vimalvijay.mymodule.main.model.Hero
import com.vimalvijay.mymodule.main.repository.MainRepository
import com.vimalvijay.mymodule.network.responsehandler.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
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
    fun heroListLiveData() = liveData(coroutineContext) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getHerosList()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message!!))
            e.printStackTrace()
        }
    }

    fun cancelAllRequest() {
        coroutineContext.cancel()
    }
}
