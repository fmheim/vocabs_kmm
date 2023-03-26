package com.example.vocabs_kmm

import assertk.assertThat
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatChoice
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatCompletionResponse
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatMessage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhrase
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue
import assertk.assertions.isEqualTo

class CommonGreetingTest {

    @Test
    fun testExample() {
        assertTrue(Greeting().greet().contains("Hello"), "Check 'Hello' is mentioned")
    }
}

class CommonUseCaseTest {

    @Test
    fun `test that the vocab to phrase use case splits the phrase correctly`() = runBlocking {
        val vocToPhraseMock = VocabToPhrase(object : VocabToPhraseClient {
            override suspend fun requestToGeneratePhrase(
                language: String,
                vocab: String
            ): ChatCompletionResponse {
                return ChatCompletionResponse(
                    choices = arrayOf(
                        ChatChoice(
                            message = ChatMessage(
                                role = "assistant",
                                content = "The green #house was beautiful"
                            ), finishReason = "", index = 0
                        )
                    )
                )
            }
        })

        val result = vocToPhraseMock.execute("English", "house")
        assertThat(result.data?.beforeVocab).isEqualTo("The green ")
        assertThat(result.data?.vocab).isEqualTo("house")
        assertThat(result.data?.afterVocab).isEqualTo(" was beautiful")

        val vocToPhraseMock2 = VocabToPhrase(object : VocabToPhraseClient {
            override suspend fun requestToGeneratePhrase(
                language: String,
                vocab: String
            ): ChatCompletionResponse {
                return ChatCompletionResponse(
                    choices = arrayOf(
                        ChatChoice(
                            message = ChatMessage(
                                role = "assistant",
                                content = "The green house was #beautiful"
                            ), finishReason = "", index = 0
                        )
                    )
                )
            }
        })

        val result2 = vocToPhraseMock2.execute("English", "The")
        assertThat(result2.data?.beforeVocab).isEqualTo("The green house was ")
        assertThat(result2.data?.vocab).isEqualTo("beautiful")
        assertThat(result2.data?.afterVocab).isEqualTo("")



    }
}