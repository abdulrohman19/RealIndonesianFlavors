package com.best.realindonesianflavors.utils

fun countRate(rating: Double): Int {
    val clampedRating = rating.coerceIn(0.0, 5.0)
    return when (clampedRating) {
        in 0.0..1.0 -> 1
        in 1.0..2.0 -> 2
        in 2.0..3.0 -> 3
        in 3.0..4.0 -> 4
        in 4.0..5.0 -> 5
        else -> 0
    }
}