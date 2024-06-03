package com.app.myapplication.data.repository

import com.app.myapplication.data.local.TrackerDao
import com.app.myapplication.data.mapper.toTrackableFood
import com.app.myapplication.data.mapper.toTrackedFood
import com.app.myapplication.data.mapper.toTrackedFoodEntity
import com.app.myapplication.data.networking.safeApiCall
import com.app.myapplication.data.remote.OpenFoodApi
import com.app.myapplication.domain.model.TrackableFood
import com.app.myapplication.domain.model.TrackedFood
import com.app.myapplication.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        /*val result = safeApiCall {
            api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
        }*/
        return try {
            val searchDto = api.searchFood(
                    query = query,
                    page = page,
                    pageSize = pageSize
                )

            Result.success(
                searchDto.products
                    .filter {
                        val carbohydrates100g = it.nutriments.carbohydrates100g ?: 0.0
                        val proteins100g = it.nutriments.proteins100g ?: 0.0
                        val fat100g = it.nutriments.fat100g ?: 0.0
                        val energyKcal100g = it.nutriments.energyKcal100g ?: 0.0
                        val calculatedCalories =
                            carbohydrates100g * 4f +
                                    proteins100g * 4f +
                                    fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull { it.toTrackableFood() }
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }

}