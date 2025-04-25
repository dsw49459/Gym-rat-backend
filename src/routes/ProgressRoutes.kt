package com.androiddevs.routes

import com.androiddevs.data.addProgressEntry
import com.androiddevs.data.deleteProgressEntry
import com.androiddevs.data.getProgressForExercise
import com.androiddevs.data.collections.ProgressEntry
import com.androiddevs.data.requests.ProgressEntryRequest
import com.androiddevs.data.requests.DeleteProgressRequest
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.ContentTransformationException
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.progressRoutes() {
    route("/progress") {
        authenticate {
            post {
                val principal = call.principal<UserIdPrincipal>() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                val request = try {
                    call.receive<ProgressEntryRequest>()
                } catch (e: ContentTransformationException) {
                    return@post call.respond(HttpStatusCode.BadRequest)
                }

                val entry = ProgressEntry(
                    exerciseId = request.exerciseId,
                    date = request.date,
                    reps = request.reps,
                    sets = request.sets,
                    weight = request.weight,
                    owner = principal.name
                )

                val success = addProgressEntry(entry)
                if (success) {
                    call.respond(HttpStatusCode.Created)
                } else {
                    call.respond(HttpStatusCode.InternalServerError, "Nie udało się zapisać progresu")
                }
            }

            get("/{exerciseId}") {
                val principal = call.principal<UserIdPrincipal>() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                val exerciseId = call.parameters["exerciseId"] ?: return@get call.respond(HttpStatusCode.BadRequest)

                val result = getProgressForExercise(principal.name, exerciseId)
                call.respond(HttpStatusCode.OK, result)
            }

            post("/delete") {
                val request = try {
                    call.receive<DeleteProgressRequest>()
                } catch (e: ContentTransformationException) {
                    return@post call.respond(HttpStatusCode.BadRequest)
                }

                val success = deleteProgressEntry(request.id)
                if (success) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}