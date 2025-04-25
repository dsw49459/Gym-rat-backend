package com.androiddevs.data.requests


data class ProgressEntryRequest(
    val exerciseId: String,
    val date: String,
    val reps: Int,
    val sets: Int,
    val weight: Double
)
