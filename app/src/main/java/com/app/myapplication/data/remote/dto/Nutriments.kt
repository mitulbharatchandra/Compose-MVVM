package com.app.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class Nutriments(
    @Json(name = "carbohydrates_100g")
    val carbohydrates100g: Double? = 0.0,
    @Json(name = "energy-kcal_100g")
    val energyKcal100g: Double? = 0.0,
    @Json(name = "fat_100g")
    val fat100g: Double? = 0.0,
    @Json(name = "proteins_100g")
    val proteins100g: Double? = 0.0
)
