package com.vimalvijay.mymodule.commonutils

import android.util.Log
import com.vimalvijay.mymodule.network.responsehandler.NetworkResultHandler
import retrofit2.Response

open class BaseRepository {

    /**
     * Api Calling
     */
    suspend fun <T : Any> safeApiRequest(
        call: suspend () -> Response<T>
    ): T? {
        val result: NetworkResultHandler<T> = safeApiResult(call)
        var data: T? = null

        when (result) {
            is NetworkResultHandler.OnSuccessResponse ->
                data = result.response
            is NetworkResultHandler.OnFailureResponse -> {
                Log.d("BaseRepository", "Exception - ${result.exception}")
                throw Exception(result.exception.message)
            }
        }

        return data
    }

    /**
     * NetWork Result Handler
     */
    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): NetworkResultHandler<T> {
        val response = call.invoke()
        if (response.isSuccessful) return NetworkResultHandler.OnSuccessResponse(
            response.body()!!
        )

        return NetworkResultHandler.OnFailureResponse(
            Exception(response.message())
        )

    }
}