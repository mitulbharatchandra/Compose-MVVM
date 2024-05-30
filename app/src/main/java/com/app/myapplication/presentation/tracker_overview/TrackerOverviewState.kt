package com.app.myapplication.presentation.tracker_overview

import com.app.myapplication.domain.model.TrackedFood
import java.time.LocalDate

data class TrackerOverviewState(
    val totalCarbs: Int = 50,
    val totalProtein: Int = 80,
    val totalFat: Int = 20,
    val totalCalories: Int = 20,
    val carbsGoal: Int = 150,
    val proteinGoal: Int = 200,
    val fatGoal: Int = 100,
    val caloriesGoal: Int = 130,
    val date: LocalDate = LocalDate.now(),
    val trackedFoods: List<TrackedFood> = emptyList(),
    val meals: List<Meal> = defaultMeals
)