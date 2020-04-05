package com.example.androidhomework2sem.model.repository

import com.example.androidhomework2sem.model.api.ApiFactory

object FoodRepositoryProvider {
    fun provideFoodRepository(): FoodRepository {
        return FoodRepository(
            ApiFactory
        )
    }
}
