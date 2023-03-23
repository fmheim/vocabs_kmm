package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhrase
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardEvent
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidVocabToFlashCardViewModel @Inject constructor(private val vocabToPhrase: VocabToPhrase): ViewModel() {

    private val viewModel by lazy {
        VocabToFlashcardViewModel(coroutineScope = viewModelScope, vocabToPhrase = vocabToPhrase)
    }

    val state = viewModel.state

    fun onEvent(event: VocabToFlashcardEvent){
        viewModel.onEvent(event)
    }
}