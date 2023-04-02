package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard.SaveAsFlashcard
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.GenerateImage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescription
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardEvent
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVocabToFlashCardViewModel @Inject constructor(
    private val vocabToPhraseWithImageDescription: VocabToPhraseWithImageDescription,
    private val saveAsFlashcard: SaveAsFlashcard,
    private val generateImage: GenerateImage
) : ViewModel() {

    private val viewModel by lazy {
        VocabToFlashcardViewModel(
            coroutineScope = viewModelScope,
            vocabToPhraseWithImageDescription = vocabToPhraseWithImageDescription,
            saveAsFlashcard = saveAsFlashcard,
            generateImage = generateImage

        )
    }

    val state = viewModel.state

    fun onEvent(event: VocabToFlashcardEvent) {
        viewModel.onEvent(event)
    }
}