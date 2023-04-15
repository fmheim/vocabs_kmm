package com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase

import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatChoice
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatCompletionResponse
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.dto.ChatMessage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescriptionClient

class FakeVocabToPhraseWithImageDescriptionClient : VocabToPhraseWithImageDescriptionClient {
    var chatCompletionResponse = ChatCompletionResponse(
        choices = arrayOf(
            ChatChoice(
                message = ChatMessage(
                    role = "assistant",
                    content = "The green #house was beautiful. %A realistic image of a beautiful green house with a blue sky and green grass in the background."
                ), finishReason = "",
                index = 0
            )
        )
    )

    override suspend fun requestToGeneratePhraseWithImageDescription(
        language: String,
        vocab: String
    ): ChatCompletionResponse {
       return chatCompletionResponse
    }
}