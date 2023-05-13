package com.example.vocabs_kmm.study.all.presentation

import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.study.all.domain.GetAllFlashcardsInCertainLanguage
import com.example.vocabs_kmm.study.random.domain.flashcard.FlashcardError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyAllViewModel(coroutineScope: CoroutineScope?, private val getAllFlashcardsInCertainLanguage: GetAllFlashcardsInCertainLanguage) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(StudyAllState())
    val state = _state.asStateFlow()

    fun onEvent(event: StudyAllEvent){
        when(event){
            StudyAllEvent.CloseLanguageDropDown -> _state.update { it.copy(isChoosingLanguage = false) }
            StudyAllEvent.OpenLanguageDropDown  -> _state.update { it.copy(isChoosingLanguage = true) }
            is StudyAllEvent.SelectLanguage     -> {
                _state.update {
                    it.copy(
                        selectedLanguage = event.language,
                        currentFlashCards = null
                    )

                }
                loadFlashcards(event.language.language.langCode)
            }

            is StudyAllEvent.LoadFlashCards     -> loadFlashcards(event.languageCode)
            StudyAllEvent.BackClick             -> Unit //handled by navigation
            StudyAllEvent.OnErrorSeen           -> _state.update { it.copy(error = null) }
        }
    }


    private fun loadFlashcards(language: String){
        _state.update { it.copy(isLoadingFlashcards = true) }
        when(val result = getAllFlashcardsInCertainLanguage.execute(language)){
            is Resource.Error   -> _state.update { it.copy(error = FlashcardError.N0_FLASHCARD_FOUND, isLoadingFlashcards = false) }
            is Resource.Success -> {
                viewModelScope.launch {
                    result.data?.collect{flashcards ->
                        _state.update { it.copy(currentFlashCards = flashcards, isLoadingFlashcards = false) }
                    }
                }
            }
        }
    }
}