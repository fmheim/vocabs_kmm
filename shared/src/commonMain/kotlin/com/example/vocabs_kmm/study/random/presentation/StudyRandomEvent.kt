package com.example.vocabs_kmm.study.random.presentation

import com.example.vocabs_kmm.core.presentation.UiLanguage

sealed class StudyRandomEvent {
    object ShowNextCard: StudyRandomEvent()
    data class SelectLanguage(val language: UiLanguage): StudyRandomEvent()
    object OpenLanguageDropDown: StudyRandomEvent()
    object CloseLanguageDropDown: StudyRandomEvent()
    object ShowAnswer: StudyRandomEvent()
    object BackClick: StudyRandomEvent()
    object OnErrorSeen: StudyRandomEvent()
}