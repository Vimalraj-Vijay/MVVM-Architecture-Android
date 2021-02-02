package com.vimalvijay.mymodule.network.interceptors

import android.content.Context
import com.vimalvijay.mymodule.R
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class LoggingInterceptor(var context: Context) : Interceptor {

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        try {
            val response = chain.proceed(request)

            val bodyString = response.body()!!.string()

            return response.newBuilder()
                .body(ResponseBody.create(response.body()?.contentType(), bodyString))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
            var msg = ""
            when (e) {
                is SocketTimeoutException -> {
                    msg = context.resources.getString(R.string.error_timeout)
                }
                is UnknownHostException -> {
                    msg = context.resources.getString(R.string.error_no_connection)
                }
                is ConnectionShutdownException -> {
                    msg = context.resources.getString(R.string.error_shutdown_connection)
                }
                is IOException -> {
                    msg = context.resources.getString(R.string.error_internal_server)
                }
                is IllegalStateException -> {
                    msg = "${e.message}"
                }
                else -> {
                    msg = "${e.message}"
                }
            }


            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(999)
                .message(msg)
                .body(ResponseBody.create(null, "{${e}}")).build()
        }
    }
}