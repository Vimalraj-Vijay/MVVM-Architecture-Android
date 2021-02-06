package com.vimalvijay.mymodule.network.interceptors

import com.vimalvijay.mymodule.commonutils.SessionDataManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(var sessionDataManager: SessionDataManager) : Interceptor {
    private var requestBuilder: Request.Builder? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val original = chain.request()
            requestBuilder = original.newBuilder().header("Authorization", sessionDataManager.authToken)
                    .header("channel", "mobile")
                    .method(original.method(), original.body())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val request = requestBuilder!!.build()
        return chain.proceed(request)
    }
}