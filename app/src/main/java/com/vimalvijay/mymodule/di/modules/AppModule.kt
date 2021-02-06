package com.vimalvijay.mymodule.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vimalvijay.mymodule.BuildConfig
import com.vimalvijay.mymodule.R
import com.vimalvijay.mymodule.commonutils.CustomProgressbar
import com.vimalvijay.mymodule.commonutils.SessionDataManager
import com.vimalvijay.mymodule.network.api.ApiConstants
import com.vimalvijay.mymodule.network.api.ApiService
import com.vimalvijay.mymodule.network.interceptors.AuthInterceptor
import com.vimalvijay.mymodule.network.interceptors.LoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    /**
     * Application's Base URL
     */
    @Provides
    fun providesAppBaseURL() = ApiConstants.BASE_URL

    /**
     * Logger For API
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context,sessionDataManager: SessionDataManager): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(AuthInterceptor(sessionDataManager))
        okHttpClient.addInterceptor(LoggingInterceptor(context))
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(loggingInterceptor)
        }
        return okHttpClient.build()
    }


    /**
     * Init Retrofit Client for Network
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    /**
     * Api Provider
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(
        ApiService::class.java
    )


    /**
     * Custom Progress Bar
     */
    @Provides
    @Singleton
    fun provideCustomProgressBar(): CustomProgressbar {
        return CustomProgressbar()
    }

    /**
     * SessionDataManager
     */
    @Provides
    @Singleton
    fun provideSessionManager(application: Application): SessionDataManager {
        return SessionDataManager(application.getSharedPreferences(application.resources.getString(R.string.app_name), Context.MODE_PRIVATE))
    }

}