package com.vimalvijay.dagger2.network

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    /**
     * Api Calling
     */
    suspend fun <T : Any> safeApiRequest(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): T? {
        val result: NetworkResultHandler<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is NetworkResultHandler.OnSuccessResponse ->
                data = result.response
            is NetworkResultHandler.OnFailureResponse ->
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
        }

        return data
    }

    /**
     * NetWork Result Handler
     */
    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): NetworkResultHandler<T> {
        val response = call.invoke()
        if (response.isSuccessful) return NetworkResultHandler.OnSuccessResponse(response.body()!!)

        return NetworkResultHandler.OnFailureResponse(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}