package com.example.androidhomework2sem.di.module

import com.example.androidhomework2sem.model.api.FoodAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun bindFoodService(retrofit: Retrofit): FoodAPI = retrofit.create(
        FoodAPI::class.java
    )
}
