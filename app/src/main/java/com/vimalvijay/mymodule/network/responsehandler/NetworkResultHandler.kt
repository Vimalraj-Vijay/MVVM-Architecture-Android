package com.vimalvijay.mymodule.network.responsehandler

sealed class NetworkResultHandler<out T : Any> {
    data class OnSuccessResponse<out T : Any>(val response: T) : NetworkResultHandler<T>()
    data class OnFailureResponse(val exception: Exception) : NetworkResultHandler<Nothing>()
}