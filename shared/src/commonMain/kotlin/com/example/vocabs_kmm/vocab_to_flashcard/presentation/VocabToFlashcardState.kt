package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.ExamplePhrase

data class VocabToFlashcardState(
    val selectedLanguage: UiLanguage = UiLanguage.byCode("en"),
    val vocabInput: String? = null,
    val submittedVocab: String? = null,
    val phrase: ExamplePhrase? = null,
    val isGeneratingText: Boolean = false,
    val isGeneratingImage: Boolean = false,
    val imageDescription: String? = null,
    val isChoosingLanguage: Boolean = false,
    val lastSavedFlashCard: Flashcard? = null,
    val showThatHasSavedFlashcard: Boolean = false,
    val imageUrl: String? = null,
    val image: ByteArray? = null,
    val error: Exception? = null
) {

    //recommended to override because of ByteArray
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as VocabToFlashcardState

        if (selectedLanguage != other.selectedLanguage) return false
        if (vocabInput != other.vocabInput) return false
        if (submittedVocab != other.submittedVocab) return false
        if (phrase != other.phrase) return false
        if (isGeneratingText != other.isGeneratingText) return false
        if (isGeneratingImage != other.isGeneratingImage) return false
        if (imageDescription != other.imageDescription) return false
        if (isChoosingLanguage != other.isChoosingLanguage) return false
        if (lastSavedFlashCard != other.lastSavedFlashCard) return false
        if (showThatHasSavedFlashcard != other.showThatHasSavedFlashcard) return false
        if (imageUrl != other.imageUrl) return false
        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int {
        var result = selectedLanguage.hashCode()
        result = 31 * result + (vocabInput?.hashCode() ?: 0)
        result = 31 * result + (submittedVocab?.hashCode() ?: 0)
        result = 31 * result + (phrase?.hashCode() ?: 0)
        result = 31 * result + isGeneratingText.hashCode()
        result = 31 * result + isGeneratingImage.hashCode()
        result = 31 * result + (imageDescription?.hashCode() ?: 0)
        result = 31 * result + isChoosingLanguage.hashCode()
        result = 31 * result + (lastSavedFlashCard?.hashCode() ?: 0)
        result = 31 * result + showThatHasSavedFlashcard.hashCode()
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + (image?.contentHashCode() ?: 0)
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}
