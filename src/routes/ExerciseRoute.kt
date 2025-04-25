package com.androiddevs.routes

import com.androiddevs.data.getAllExercises
import com.androiddevs.data.getExerciseById
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.exerciseRoutes() {
    route("/exercises") {
        get {
            val exercises = getAllExercises()
            call.respond(HttpStatusCode.OK, exercises)
        }
    }

    route("/exercise/{id}") {
        get {
            val exerciseId = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)

            val exercise = getExerciseById(exerciseId)
            if (exercise != null) {
                call.respond(HttpStatusCode.OK, exercise)
            } else {
                call.respond(HttpStatusCode.NotFound, "Ćwiczenie nie znalezione")
            }
        }
    }
}