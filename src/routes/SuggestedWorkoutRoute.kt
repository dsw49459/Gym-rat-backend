package com.androiddevs.routes

import com.androiddevs.data.getAllExercises
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlin.text.get

fun Route.suggestedWorkoutRoute() {
    route("/suggested-workout") {
        get {
            val exercises = getAllExercises() // użyjemy funkcji, którą już masz
            val suggestedWorkout = exercises.shuffled().take(3) // 3 losowe ćwiczenia
            call.respond(suggestedWorkout)
        }
    }
}