package com.example.vocabs_kmm.vocab_to_phrase.data.remote


import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import platform.posix.getenv


actual class OpenAiHttpClientFactory {


    actual fun create(): HttpClient {
        val apiKey = requireNotNull(getenv("OPENAI_API_KEY")?.toString()){
            "OPENAI_API_KEY environment variable must be set."
        }

        return HttpClient(Darwin){
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            apiKey, apiKey
                        )
                    }
                }
            }
        }
    }
}