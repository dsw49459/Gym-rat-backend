package com.androiddevs.data.collections

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Note(
    val title: String,
    val content: String,
    val color: Long,
    val owner: String,
    @BsonId
    val id: String = ObjectId().toString()
)