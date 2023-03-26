package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhrase
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VocabToFlashcardViewModel(
    coroutineScope: CoroutineScope?, private val vocabToPhrase: VocabToPhrase
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(VocabToFlashcardState())
    val state = _state.asStateFlow()


    private var phraseGenerateJob: Job? = null

    fun onEvent(event: VocabToFlashcardEvent) {
        when (event) {
            is VocabToFlashcardEvent.GenerateImage      -> TODO()
            is VocabToFlashcardEvent.GeneratePhrase     -> generatePhrase(currentState = state.value)
            is VocabToFlashcardEvent.Error              -> TODO()
            is VocabToFlashcardEvent.SaveFlashcard      -> TODO()
            is VocabToFlashcardEvent.VocabInputChanged  -> _state.update { it.copy(vocab = event.text) }
            is VocabToFlashcardEvent.SelectLanguage     -> _state.update { it.copy(language = event.language, phrase = null) }
            VocabToFlashcardEvent.OpenLanguageDropDown  -> _state.update {
                it.copy(
                    isChoosingLanguage = true
                )
            }

            VocabToFlashcardEvent.CloseLanguageDropDown -> _state.update {
                it.copy(
                    isChoosingLanguage = false
                )
            }
        }
    }

    private fun generatePhrase(currentState: VocabToFlashcardState) {
        if (phraseGenerateJob?.isActive == true) return
        phraseGenerateJob = viewModelScope.launch {
            _state.update { it.copy(isGenerating = true) }
            when (val result = vocabToPhrase.execute(
                languageName = currentState.language.language.langName, vocab = currentState.vocab
            )) {
                is Resource.Success -> {
                    _state.update { it.copy(phrase = result.data, isGenerating = false) }
                }

                is Resource.Error   -> {
                    _state.update {
                        it.copy(
                            phrase = null,
                            isGenerating = false,
                            vocabToPhraseError = (result.throwable as? VocabToPhraseException)?.error
                        )
                    }
                }
            }
        }
    }
}