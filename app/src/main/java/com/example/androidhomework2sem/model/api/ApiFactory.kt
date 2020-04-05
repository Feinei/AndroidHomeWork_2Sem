package com.example.androidhomework2sem.model.api

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.example.androidhomework2sem.BuildConfig
import com.example.androidhomework2sem.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object ApiFactory {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url().newBuilder()
            .addQueryParameter(Constants.CATEGORY, Constants.CATEGORY_1)
            .addQueryParameter(Constants.CATEGORY, Constants.CATEGORY_2)
            .addQueryParameter(Constants.CATEGORY, Constants.CATEGORY_3)
            .addQueryParameter("app_id", Constants.APP_ID)
            .addQueryParameter("app_key", BuildConfig.API_KEY)
            .build()

        val newRequest = chain.request().newBuilder().url(newUrl).build()
        chain.proceed(newRequest)
    }

    private val client by lazy {
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_ENDPOINT)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val foodApi: FoodAPI by lazy {
        retrofit.create(FoodAPI::class.java)
    }
}
