package com.app.myapplication.data.mapper

import com.app.myapplication.data.local.entity.TrackedFoodEntity
import com.app.myapplication.domain.model.MealType
import com.app.myapplication.domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        date = LocalDate.of(year, month, dayOfMonth),
        id = id
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        imageUrl = imageUrl,
        type = mealType.name,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        id = id
    )
}