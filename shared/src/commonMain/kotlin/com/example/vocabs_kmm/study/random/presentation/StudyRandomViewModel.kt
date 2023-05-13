package com.example.vocabs_kmm.study.random.presentation

import com.example.vocabs_kmm.study.random.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.study.random.domain.flashcard.FlashcardException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyRandomViewModel(
    coroutineScope: CoroutineScope?,
    private val getRandomFlashcard: GetRandomFlashcard
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(StudyRandomState())
    val state = _state.asStateFlow()

    fun onEvent(event: StudyRandomEvent) {
        when (event) {
            StudyRandomEvent.CloseLanguageDropDown -> _state.update { it.copy(isChoosingLanguage = false) }
            StudyRandomEvent.OpenLanguageDropDown  -> _state.update { it.copy(isChoosingLanguage = true) }
            is StudyRandomEvent.SelectLanguage     -> _state.update {
                it.copy(
                    selectedLanguage = event.language,
                    currentFlashCard = null
                )
            }

            StudyRandomEvent.ShowAnswer            -> _state.update { it.copy(isShowingAnswer = true) }
            StudyRandomEvent.ShowNextCard          -> showNextCard()
            StudyRandomEvent.OnErrorSeen           -> _state.update { it.copy(error=null) }
            StudyRandomEvent.BackClick             -> Unit //handled in navigation
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
