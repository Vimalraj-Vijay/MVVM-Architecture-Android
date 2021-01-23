package com.vimalvijay.dagger2.network

sealed class NetworkResultHandler<out T : Any> {
    data class OnSuccessResponse<out T : Any>(val response: T) : NetworkResultHandler<T>()
    data class OnFailureResponse(val exception: Exception) : NetworkResultHandler<Nothing>()
}