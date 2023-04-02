package com.example.vocabs_kmm

import assertk.assertThat
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatChoice
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatCompletionResponse
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatMessage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescription
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescriptionClient
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue
import assertk.assertions.isEqualTo

class CommonGreetingTest {


}

class CommonUseCaseTest {

    @Test
    fun `test that the vocab to phrase use case splits the phrase correctly`() = runBlocking {
        val vocToPhraseMock = VocabToPhraseWithImageDescription(object : VocabToPhraseWithImageDescriptionClient {
            override suspend fun requestToGeneratePhraseWithImageDescription(
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
        assertThat(result.data?.examplePhrase?.beforeVocab).isEqualTo("The green ")
        assertThat(result.data?.examplePhrase?.vocab).isEqualTo("house")
        assertThat(result.data?.examplePhrase?.afterVocab).isEqualTo(" was beautiful")

        val vocToPhraseMock2 = VocabToPhraseWithImageDescription(object : VocabToPhraseWithImageDescriptionClient {
            override suspend fun requestToGeneratePhraseWithImageDescription(
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
        assertThat(result2.data?.examplePhrase?.beforeVocab).isEqualTo("The green house was ")
        assertThat(result2.data?.examplePhrase?.vocab).isEqualTo("beautiful")
        assertThat(result2.data?.examplePhrase?.afterVocab).isEqualTo("")



    }
}