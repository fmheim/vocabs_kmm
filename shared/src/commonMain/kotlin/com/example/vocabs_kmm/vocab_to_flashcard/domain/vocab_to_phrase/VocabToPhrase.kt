package com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase

import com.example.vocabs_kmm.core.domain.util.Resource

class VocabToPhrase(private val client: VocabToPhraseClient) {
    suspend fun execute(
        languageName: String, vocab: String?
    ): Resource<ExamplePhrase> {
        return try {
            if (vocab.isNullOrBlank()) throw VocabToPhraseException(VocabToPhraseError.CLIENT_ERROR)
            val response = client.requestToGeneratePhrase(languageName, vocab)


            val responsePhrase = response.choices?.firstOrNull()?.message?.content
            if (responsePhrase.isNullOrBlank()) throw VocabToPhraseException(VocabToPhraseError.SERVER_ERROR)
            Resource.Success(separateSentence(responsePhrase))
        } catch (e: VocabToPhraseException) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}

private fun separateSentence(sentence: String): ExamplePhrase {
    val parts = sentence.split("#")
    if(parts.size <= 1) return ExamplePhrase(beforeVocab = sentence, vocab = "", afterVocab = "")
    val before = parts[0]
    val after = parts[1]
    val vocab = after.split(" ").getOrNull(0)
    val afterVocab = after.subSequence(range = (vocab?.length ?: 0)..after.lastIndex)
    return ExamplePhrase(beforeVocab = before, vocab = vocab ?: "", afterVocab = afterVocab.toString())
}