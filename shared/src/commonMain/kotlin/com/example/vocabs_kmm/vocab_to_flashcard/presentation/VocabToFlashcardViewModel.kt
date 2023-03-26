package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.domain.flashcard.FlashcardException
import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard.SaveAsFlashcard
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
    coroutineScope: CoroutineScope?,
    private val vocabToPhrase: VocabToPhrase,
    private val saveAsFlashcard: SaveAsFlashcard
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(VocabToFlashcardState())
    val state = _state.asStateFlow()


    private var phraseGenerateJob: Job? = null

    fun onEvent(event: VocabToFlashcardEvent) {
        when (event) {
            is VocabToFlashcardEvent.GenerateImage      -> TODO()
            is VocabToFlashcardEvent.GeneratePhrase     -> generatePhrase(currentState = state.value.copy())
            is VocabToFlashcardEvent.Error              -> TODO()
            is VocabToFlashcardEvent.SaveFlashcard      -> saveAsFlashcardToDb(state.value.copy())
            is VocabToFlashcardEvent.VocabInputChanged  -> _state.update { it.copy(vocabInput = event.text) }
            is VocabToFlashcardEvent.SelectLanguage     -> _state.update {
                it.copy(
                    language = event.language, phrase = null
                )
            }
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

    private fun saveAsFlashcardToDb(currentState: VocabToFlashcardState) {
        viewModelScope.launch {
            val result = saveAsFlashcard.execute(
                examplePhrase = currentState.phrase,
                languageCode = currentState.language.language.langCode,
                vocab = currentState.vocabInput
            )
            when(result){
                is Resource.Error   -> _state.update { it.copy(error = result.throwable as? FlashcardException) }
                is Resource.Success -> _state.update { it.copy(lastSavedFlashCard = result.data) }
            }
            _state.update { it.copy(lastSavedFlashCard = result.data) }
        }
    }

    private fun generatePhrase(currentState: VocabToFlashcardState) {
        if (phraseGenerateJob?.isActive == true) return
        phraseGenerateJob = viewModelScope.launch {
            _state.update { it.copy(isGenerating = true, submittedVocab = it.vocabInput) }
            when (val result = vocabToPhrase.execute(
                languageName = currentState.language.language.langName, vocab = currentState.vocabInput
            )) {
                is Resource.Success -> {
                    _state.update { it.copy(phrase = result.data, isGenerating = false) }
                }

                is Resource.Error   -> {
                    _state.update {
                        it.copy(
                            phrase = null,
                            isGenerating = false,
                            error = (result.throwable as? VocabToPhraseException)
                        )
                    }
                }
            }
        }
    }
}