package com.example.androidhomework2sem.model.repository

import com.example.androidhomework2sem.model.api.FoodAPI
import com.example.androidhomework2sem.model.api.FoodResponse
import io.reactivex.Observable
import javax.inject.Inject

class FoodRepository @Inject constructor(val apiService: FoodAPI) {
    fun searchFood(name: String): Observable<FoodResponse> {
        return apiService.foodByName(name = name)
    }
}
