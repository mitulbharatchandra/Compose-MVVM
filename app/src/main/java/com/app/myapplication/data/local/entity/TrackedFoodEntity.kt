package com.app.myapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrackedFoodEntity(
    val name: String,
    val imageUrl: String? = null,
    val type: String,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    @PrimaryKey val id: Int? = null
)