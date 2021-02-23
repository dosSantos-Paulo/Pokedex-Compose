package com.devdossantos.pokedexcompose.api.utils

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorClient {

    companion object {
        fun createHttpClient(): HttpClient {
            return HttpClient(OkHttp) {

                install(JsonFeature) {
                    this.serializer = GsonSerializer() {
                        serializeNulls()
                    }
                }

                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("Ktor", message)
                        }
                    }
                    level = LogLevel.ALL
                }

                install(HttpTimeout) {
                    requestTimeoutMillis = 15000L
                    connectTimeoutMillis = 15000L
                    socketTimeoutMillis = 15000L
                }

                defaultRequest {

                    if (this.method != HttpMethod.Get) contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
//                    url(
//                        scheme = "https",
//                        host = "pokeapi.co",
//                        port = DEFAULT_PORT,
//                        path = path
//                    )
                }
            }
        }
    }

}