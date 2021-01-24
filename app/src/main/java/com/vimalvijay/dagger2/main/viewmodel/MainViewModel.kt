package com.vimalvijay.dagger2.main.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vimalvijay.dagger2.main.model.Hero
import com.vimalvijay.dagger2.main.repository.MainRepository
import com.vimalvijay.dagger2.network.utils.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainViewModel @ViewModelInject constructor(private var mainRepository: MainRepository) : ViewModel() {

    /**
     * Launch API using Coroutines
     */
    fun fetchHerosList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getHerosList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}
