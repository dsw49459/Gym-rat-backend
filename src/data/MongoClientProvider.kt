package com.androiddevs.data

import org.litote.kmongo.coroutine.*
import org.litote.kmongo.reactivestreams.KMongo

object MongoClientProvider {
    private val connectionString: String by lazy {
        System.getenv("MONGODB_URI") ?:
        "mongodb+srv://ysiu16:IS3z1BGLDsga46AE@gymrat.fotkcyz.mongodb.net/?retryWrites=true&w=majority&appName=GymRat"
    }

    val client: CoroutineClient by lazy {
        KMongo.createClient(connectionString).coroutine
    }

    val database: CoroutineDatabase by lazy {
        client.getDatabase("GymRat")
    }
}