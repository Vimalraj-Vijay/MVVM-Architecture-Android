package com.vimalvijay.mymodule.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vimalvijay.mymodule.main.repository.MainRepository
import com.vimalvijay.mymodule.network.responsehandler.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(private var mainRepository: MainRepository) :
    ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.IO

    /**
     * Launch API using liveData
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
}
