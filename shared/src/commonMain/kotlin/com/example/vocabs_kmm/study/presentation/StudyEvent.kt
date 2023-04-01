package com.example.vocabs_kmm.study.presentation

import com.example.vocabs_kmm.core.presentation.UiLanguage

sealed class StudyEvent {
    object ShowNextCard: StudyEvent()
    data class SelectLanguage(val language: UiLanguage): StudyEvent()
    object OpenLanguageDropDown: StudyEvent()
    object CloseLanguageDropDown: StudyEvent()
    object ShowAnswer: StudyEvent()
    object BackClick: StudyEvent()
    object OnErrorSeen: StudyEvent()
}