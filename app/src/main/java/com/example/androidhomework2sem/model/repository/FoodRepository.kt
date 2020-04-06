package com.example.androidhomework2sem.model.repository

import com.example.androidhomework2sem.model.api.FoodResponse
import com.example.androidhomework2sem.model.api.ApiFactory
import io.reactivex.Observable

class FoodRepository(val apiService: ApiFactory) {
    fun searchFood(name: String): Observable<FoodResponse> {
        return apiService.foodApi.foodByName(name = name)
    }
}
