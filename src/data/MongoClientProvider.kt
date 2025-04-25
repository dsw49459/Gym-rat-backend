package com.androiddevs.data

import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo

object MongoClientProvider {
    private val connectionString = System.getenv("MONGO_URI")
        ?: "mongodb+srv://ysiu16:YOUR_PASSWORD@gymrat.fotkcyz.mongodb.net/?retryWrites=true&w=majority&appName=GymRat"

    val client: CoroutineClient = KMongo.createClient(connectionString).coroutine
    val database: CoroutineDatabase = client.getDatabase("GymRat") // Nazwa Twojej bazy na Atlasie
}
