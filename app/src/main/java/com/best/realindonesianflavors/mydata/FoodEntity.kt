package com.best.realindonesianflavors.mydata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int? =null,
    val name: String,
    val description: String,
    val location: String,
    val photo: Int,
    val rating: Double,
    val totalReview: Int,
    var isFavorite: Boolean = false,
)