package com.example.vocabs_kmm.android.study.random.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabs_kmm.study.random.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.study.random.presentation.StudyRandomEvent
import com.example.vocabs_kmm.study.random.presentation.StudyRandomViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidStudyRandomViewModel @Inject constructor(getRandomFlashcard: GetRandomFlashcard) :
    ViewModel() {

    private val viewModel =
        StudyRandomViewModel(coroutineScope = viewModelScope, getRandomFlashcard = getRandomFlashcard)

    val state = viewModel.state
    fun onEvent(event: StudyRandomEvent) {
        viewModel.onEvent(event)
    }
}
