package com.example.androidhomework2sem.model.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodAPI {

    @GET("parser")
    fun foodByName(@Query("ingr") name: String): Observable<FoodResponse>
}
