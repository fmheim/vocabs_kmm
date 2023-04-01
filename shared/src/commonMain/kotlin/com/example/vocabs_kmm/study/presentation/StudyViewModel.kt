package com.example.vocabs_kmm.study.presentation

import com.example.vocabs_kmm.study.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.study.domain.flashcard.FlashcardException
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
            StudyEvent.OnErrorSeen           -> _state.update { it.copy(error=null) }
            StudyEvent.BackClick             -> Unit //handled in navigation
        }
    }

    private fun showNextCard() {
        _state.update { it.copy(isLoadingFlashcard = true) }
        viewModelScope.launch {
            val result =
                getRandomFlashcard.execute(languageCode = state.value.selectedLanguage.language.langCode)
            when (result) {
                is Resource.Error -> _state.update {
                    it.copy(
                        error = (result.throwable as? FlashcardException)?.error,
                        isShowingAnswer = false,
                        isLoadingFlashcard = false
                    )
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            currentFlashCard = result.data,
                            isLoadingFlashcard = false,
                            isShowingAnswer = false
                        )
                    }
                }
            }
        }
    }
}
