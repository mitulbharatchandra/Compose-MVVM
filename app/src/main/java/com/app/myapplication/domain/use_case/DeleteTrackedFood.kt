package com.app.myapplication.domain.use_case

import com.app.myapplication.domain.model.TrackedFood
import com.app.myapplication.domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}