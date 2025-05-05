package com.androiddevs.routes

import com.androiddevs.data.checkPasswordForEmail
import com.androiddevs.data.getUserByEmail
import com.androiddevs.data.requests.AccountRequest
import com.androiddevs.data.responses.SimpleResponse
import io.ktor.application.*
import io.ktor.features.ContentTransformationException
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.loginRoute() {
    route("/login") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch(e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            val isPasswordCorrect = checkPasswordForEmail(request.email, request.password)
            if(isPasswordCorrect) {
                val user = getUserByEmail(request.email)
                call.respond(OK, SimpleResponse(true, "Zalogowales sie!", user?.id))
            } else {
                call.respond(OK, SimpleResponse(false, "E-Mail lub haslo sa niepoprawne"))
            }
        }
    }
}