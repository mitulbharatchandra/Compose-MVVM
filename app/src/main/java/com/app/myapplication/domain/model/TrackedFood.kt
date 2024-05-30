package com.app.myapplication.domain.model

import java.time.LocalDate

data class TrackedFood(
    val name: String,
    val imageUrl: String?,
    val mealType: MealType,
    val date: LocalDate,
    val id: Int? = null
)
