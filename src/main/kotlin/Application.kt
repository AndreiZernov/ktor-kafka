package ktor.kafka

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.Routing
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import java.util.UUID
import kotlinx.serialization.json.Json

fun main() {
    println("main" + "Starting server on http:localhost:8080...")

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", watchPaths = listOf("classes")) {
        mainModule()
    }.start(wait = true)
}


fun Application.mainModule() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Routing) {
        get("/") {
            call.respond(HttpStatusCode.OK, "HELLO KAFKA!")
        }
        get("/produce") {
            val kafkaProducer = buildProducer<String, AvroUser>()
            val processor = KafkaMessageProcessor(kafkaProducer, "users")

            val randomUUID = UUID.randomUUID().toString()
            val randomNumber = (Math.random() * 100).toInt()

            processor.publishUser(randomUUID, randomNumber)
            call.respond(HttpStatusCode.OK)
        }
    }

    install(BackgroundJob.BackgroundJobFeature) {
        name = "Kafka-Producer-Job"
        job = buildConsumer<String, AvroUser>()
    }
}
