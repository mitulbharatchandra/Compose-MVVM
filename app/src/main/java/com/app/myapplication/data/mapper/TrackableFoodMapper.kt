package com.app.myapplication.data.mapper

import com.app.myapplication.data.remote.dto.Product
import com.app.myapplication.domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val carbsPer100g = nutriments.carbohydrates100g?.roundToInt() ?: 0
    val proteinPer100g = nutriments.proteins100g?.roundToInt() ?: 0
    val fatPer100g = nutriments.proteins100g?.roundToInt() ?: 0
    val caloriesPer100g = nutriments.energyKcal100g?.roundToInt() ?: 0
    return TrackableFood(
        name = productName ?: return null,
        carbsPer100g = carbsPer100g,
        proteinPer100g = proteinPer100g,
        fatPer100g = fatPer100g,
        caloriesPer100g = caloriesPer100g,
        imageUrl = imageFrontThumbUrl
    )
}