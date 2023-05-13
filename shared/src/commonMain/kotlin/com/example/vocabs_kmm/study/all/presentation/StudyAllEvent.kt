package com.example.vocabs_kmm.study.all.presentation

import com.example.vocabs_kmm.core.presentation.UiLanguage

sealed class StudyAllEvent{
    data class SelectLanguage(val language: UiLanguage): StudyAllEvent()
    object OpenLanguageDropDown: StudyAllEvent()
    object CloseLanguageDropDown: StudyAllEvent()
    object BackClick: StudyAllEvent()

    object OnErrorSeen: StudyAllEvent()
    data class LoadFlashCards(val languageCode: String): StudyAllEvent()

}
