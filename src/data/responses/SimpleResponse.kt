package com.androiddevs.data.responses

data class SimpleResponse(
    val successful: Boolean,
    val message: String,
    val userId: String? = null
)