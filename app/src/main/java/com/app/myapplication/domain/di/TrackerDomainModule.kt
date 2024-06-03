package com.app.myapplication.domain.di

import android.app.Application
import com.app.myapplication.data.preferences.DefaultPreferences
import com.app.myapplication.domain.preferences.Preferences
import com.app.myapplication.domain.repository.TrackerRepository
import com.app.myapplication.domain.use_case.CalculateMealNutrients
import com.app.myapplication.domain.use_case.DeleteTrackedFood
import com.app.myapplication.domain.use_case.GetFoodsForDate
import com.app.myapplication.domain.use_case.SearchFood
import com.app.myapplication.domain.use_case.TrackFood
import com.app.myapplication.domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }

}