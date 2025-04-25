package com.androiddevs.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class ProgressEntry(
    val exerciseId: String,
    val date: String,
    val reps: Int,
    val sets: Int,
    val weight: Double,
    val owner: String,
    @BsonId
    val id: String = ObjectId().toString()
)