package com.example.study.presentation

import com.example.study.domain.flashcard.GetRandomFlashcard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StudyViewModel(coroutineScope: CoroutineScope?, private val getRandomFlashcard: GetRandomFlashcard) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(StudyState())
    val state = _state.asStateFlow()




}