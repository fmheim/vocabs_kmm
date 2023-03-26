package com.example.study.presentation

import com.example.study.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.core.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyViewModel(
    coroutineScope: CoroutineScope?,
    private val getRandomFlashcard: GetRandomFlashcard
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(StudyState())
    val state = _state.asStateFlow()

    fun onEvent(event: StudyEvent) {
        when (event) {
            StudyEvent.CloseLanguageDropDown -> _state.update { it.copy(isChoosingLanguage = false) }
            StudyEvent.OpenLanguageDropDown  -> _state.update { it.copy(isChoosingLanguage = true) }
            is StudyEvent.SelectLanguage     -> _state.update {
                it.copy(
                    selectedLanguage = event.language,
                    currentFlashCard = null
                )
            }

            StudyEvent.ShowAnswer            -> _state.update { it.copy(isShowingAnswer = true) }
            StudyEvent.ShowNextCard          -> showNextCard()
        }
    }

    private fun showNextCard() {
        _state.update{it.copy(isLoadingFlashcard = true)}
        val result =
            getRandomFlashcard.execute(languageCode = state.value.selectedLanguage.language.langCode)
        when (result) {
            is Resource.Error -> _state.update { it.copy(error = ("todo study error"), isShowingAnswer = false, isLoadingFlashcard = false) }
            is Resource.Success -> {
                viewModelScope.launch{result.data?.collect{flashcard ->
                _state.update { it.copy(currentFlashCard = flashcard, isLoadingFlashcard = false, isShowingAnswer = false) }}}
            }
        }

    }


}