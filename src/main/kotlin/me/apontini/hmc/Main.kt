package me.apontini.hmc

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import me.apontini.hmc.user.appModule
import me.apontini.hmc.collector.application.CollectorService
import me.apontini.hmc.user.userModule
import me.apontini.hmc.user.userRoutes
import org.koin.core.component.KoinComponent
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.slf4j.event.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.scheduleAtFixedRate

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    install(Koin) {
        modules(userModule)
        modules(appModule)
    }
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        json(Json { prettyPrint = true })
    }

    userRoutes()
    routing {
        get("/") {
            call.respondBytes(contentType = ContentType.Text.Html) {
                "<h1>I'm working! \uD83D\uDC0B</h1>".toByteArray(Charsets.UTF_16)
            }
        }
    }

    val collector by inject<CollectorService>()
    ScheduleCollection(collector).scheduleCollection()
}

internal class ScheduleCollection(val collectorService: CollectorService) : KoinComponent {
    fun scheduleCollection() {
        Timer(true).scheduleAtFixedRate(
            TimeUnit.SECONDS.toMillis(5),
            TimeUnit.MINUTES.toMillis(1)
        ) {
            collectorService.collect()
        }
    }
}
