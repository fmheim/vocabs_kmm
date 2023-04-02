package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

import com.example.vocabs_kmm.core.domain.util.Resource

class VocabToPhraseWithImageDescription(private val client: VocabToPhraseWithImageDescriptionClient) {
    suspend fun execute(
        languageName: String, vocab: String?
    ): Resource<ProcessedResponse> {
        return try {
            if (vocab.isNullOrBlank()) throw VocabToPhraseException(VocabToPhraseError.CLIENT_ERROR)
            val response = client.requestToGeneratePhraseWithImageDescription(languageName, vocab)
            val responsePhrase = response.choices?.firstOrNull()?.message?.content
            if (responsePhrase.isNullOrBlank()) throw VocabToPhraseException(VocabToPhraseError.SERVER_ERROR)
            val processedResponse = ProcessedResponse(
                examplePhrase = extractExamplePhraseSentence(responsePhrase),
                imageDescription = extractImageDescription(responsePhrase)
            )
            Resource.Success(processedResponse)
        } catch (e: VocabToPhraseException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

    private fun extractExamplePhraseSentence(sentence: String): ExamplePhrase {
        val parts = sentence.split("#")
        if (parts.size <= 1) return ExamplePhrase(
            beforeVocab = sentence,
            vocab = "",
            afterVocab = ""
        )
        val before = parts[0]
        val after = parts[1]
        val vocab = after.split(" ").getOrNull(0)
        val endOfExampleSentenceIndex = after.indexOf("%") - 1
        if (endOfExampleSentenceIndex < 1 || (vocab?.length ?: 0) < 1) {
            throw VocabToPhraseException(VocabToPhraseError.SERVER_ERROR)
        }
        val afterVocab = after.subSequence(range = (vocab?.length ?: 0)..endOfExampleSentenceIndex)
        return ExamplePhrase(
            beforeVocab = before, vocab = vocab ?: "", afterVocab = afterVocab.toString()
        )
    }

    private fun extractImageDescription(sentence: String): String? {
        val parts = sentence.split("%")
        if (parts.size <= 1) return null
        return parts[1].trim()
    }
}



