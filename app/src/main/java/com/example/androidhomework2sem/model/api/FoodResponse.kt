package com.example.androidhomework2sem.model.api

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("text")
    var text: String,
    @SerializedName("parsed")
    var searchInfo: List<Food>,
    @SerializedName("hints")
    var hints: List<Food>
)

data class Food(
    @SerializedName("food")
    var foodDetail: FoodDetail
)

data class FoodDetail(
    @SerializedName("label")
    var label: String,
    @SerializedName("nutrients")
    var nutrients: Nutrients,
    @SerializedName("category")
    var category: String,
    @SerializedName("image")
    var imageSource: String
)

data class Nutrients(
    @SerializedName("ENERC_KCAL")
    var cals: Double,
    @SerializedName("PRONCNT")
    var protein: Double,
    @SerializedName("FAT")
    var fat: Double,
    @SerializedName("CHOCDF")
    var carbs: Double,
    @SerializedName("FIBTG")
    var fiber: Double
)
