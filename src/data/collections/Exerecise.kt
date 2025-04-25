package com.androiddevs.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Exercise(
    val name: String,
    val imageUrl: String,
    val repsCount: Int,
    val setsCount: Int,
    @BsonId
    val id: String = ObjectId().toString()
)