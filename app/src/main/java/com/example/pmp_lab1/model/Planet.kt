package com.example.pmp_lab1.model

import androidx.annotation.DrawableRes
import com.example.pmp_lab1.R

data class Planet (
    val name: String,
    @DrawableRes val imageResId: Int,
    val galaxy: String,
    val sunDistanceKm: Double,
    val gravityAcceleration: Double
)