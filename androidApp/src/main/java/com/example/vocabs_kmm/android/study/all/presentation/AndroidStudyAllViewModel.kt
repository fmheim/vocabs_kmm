package com.example.vocabs_kmm.android.study.all.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vocabs_kmm.study.all.domain.GetAllFlashcardsInCertainLanguage
import com.example.vocabs_kmm.study.all.presentation.StudyAllEvent
import com.example.vocabs_kmm.study.all.presentation.StudyAllViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidStudyAllViewModel @Inject constructor(getAllFlashcardsInCertainLanguage: GetAllFlashcardsInCertainLanguage) :
    ViewModel() {

    private val viewModel = StudyAllViewModel(
        coroutineScope = viewModelScope,
        getAllFlashcardsInCertainLanguage = getAllFlashcardsInCertainLanguage
    )

    val state = viewModel.state

    fun onEvent(event: StudyAllEvent) = viewModel.onEvent(event)


}


