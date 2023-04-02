package com.example.vocabs_kmm.core.domain.flashcard

data class Flashcard (
    val id: Long?,
    val languageCode: String,
    val vocab: String,
    val beforeVocabText: String,
    val vocabInPhrase: String,
    val afterVocabText: String,
    val image: ByteArray
) {

    //recommended to override because of ByteArray
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Flashcard

        if (id != other.id) return false
        if (languageCode != other.languageCode) return false
        if (vocab != other.vocab) return false
        if (beforeVocabText != other.beforeVocabText) return false
        if (vocabInPhrase != other.vocabInPhrase) return false
        if (afterVocabText != other.afterVocabText) return false
        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + languageCode.hashCode()
        result = 31 * result + vocab.hashCode()
        result = 31 * result + beforeVocabText.hashCode()
        result = 31 * result + vocabInPhrase.hashCode()
        result = 31 * result + afterVocabText.hashCode()
        result = 31 * result + image.contentHashCode()
        return result
    }
}