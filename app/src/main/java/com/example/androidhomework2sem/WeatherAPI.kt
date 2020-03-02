package com.example.androidhomework2sem

import com.example.androidhomework2sem.response.WeatherList
import com.example.androidhomework2sem.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun weatherByName(@Query("q") name: String): WeatherResponse

    @GET("find")
    suspend fun weatherInNearbyCities(@Query("lon") lon: Double,
                                      @Query("lat") lat: Double,
                                      @Query("cnt") cnt: Int): Response<WeatherList>
}
