package com.example.vocabs_kmm.android.study.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabs_kmm.study.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.study.presentation.StudyEvent
import com.example.vocabs_kmm.study.presentation.StudyViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidStudyViewModel @Inject constructor(getRandomFlashcard: GetRandomFlashcard) :
    ViewModel() {

    private val viewModel =
        StudyViewModel(coroutineScope = viewModelScope, getRandomFlashcard = getRandomFlashcard)

    val state = viewModel.state
    fun onEvent(event: StudyEvent) {
        viewModel.onEvent(event)
    }
}
