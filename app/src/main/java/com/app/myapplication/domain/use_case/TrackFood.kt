package com.app.myapplication.domain.use_case

import com.app.myapplication.domain.model.MealType
import com.app.myapplication.domain.model.TrackableFood
import com.app.myapplication.domain.model.TrackedFood
import com.app.myapplication.domain.preferences.Preferences
import com.app.myapplication.domain.repository.TrackerRepository
import java.time.LocalDate

class TrackFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(
        food: TrackableFood,
        mealType: MealType,
        date: LocalDate
    ) {
        repository.insertTrackedFood(
            TrackedFood(
                name = food.name,
                imageUrl = food.imageUrl,
                mealType = mealType,
                date = date,
            )
        )
    }
}