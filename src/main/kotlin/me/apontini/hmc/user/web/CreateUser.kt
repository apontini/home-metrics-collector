package me.apontini.hmc.user.web

import me.apontini.hmc.user.application.UserService
import me.apontini.hmc.user.exceptions.UserAlreadyExistsException
import me.apontini.hmc.user.web.dto.CreateUserRequest
import me.apontini.hmc.webapp.Controller
import io.ktor.server.application.*
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreateUser : Controller(), KoinComponent {
    private val userService by inject<UserService>();

    override suspend fun PipelineContext<Unit, ApplicationCall>.call() {
        val request = call.receive<CreateUserRequest>()
        try {
            call.respond(
                HttpStatusCode.Companion.Created,
                userService.createUser(request.email, request.name)
            )
        } catch (e: UserAlreadyExistsException) {
            call.respondText(status = HttpStatusCode.Conflict) { "User already exists" }
        }
    }

}