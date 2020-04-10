package com.example.androidhomework2sem.di.module

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.example.androidhomework2sem.BuildConfig
import com.example.androidhomework2sem.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    @Named(TAG_AUTH)
    fun provideAuthInterceptor() = Interceptor { chain ->
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

    @Provides
    @Singleton
    @Named
    fun provideClient(
        @Named(TAG_AUTH) authInterceptor: Interceptor,
        @Named(TAG_LOG) loggingInterceptor: Interceptor
    ) = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory,
        adapterFactory: RxJava2CallAdapterFactory,
        @Named(TAG_BASE_URL) url: String
    ) = Retrofit.Builder()
        .client(client)
        .baseUrl(url)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(adapterFactory)
        .build()

    @Provides
    @Singleton
    @Named(TAG_LOG)
    fun provideLoggingInterceptor() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient()

    @Provides
    @Singleton
    fun provideConvertFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    @Named(TAG_BASE_URL)
    fun provideBaseURL(): String = BuildConfig.API_ENDPOINT

    companion object {
        private const val TAG_AUTH = "tag_auth"
        private const val TAG_LOG = "tag_log"
        private const val TAG_BASE_URL = "tag_base_url"
    }
}
