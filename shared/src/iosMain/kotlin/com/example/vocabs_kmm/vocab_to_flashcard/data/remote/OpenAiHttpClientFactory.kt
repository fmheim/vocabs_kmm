package com.example.vocabs_kmm.vocab_to_flashcard.data.remote


import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*

import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json



actual class OpenAiHttpClientFactory {


    actual fun create(): HttpClient {
//        val apiKey = requireNotNull(("OPENAI_API_KEY")?.toString()){
//            "OPENAI_API_KEY environment variable must be set."
//        }

        return HttpClient(Darwin){

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
                           "", "" //apiKey, apiKey
                        )
                    }
                }
            }
        }
    }
}