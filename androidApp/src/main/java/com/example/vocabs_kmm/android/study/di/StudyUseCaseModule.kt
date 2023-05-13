package com.example.vocabs_kmm.android.study.di

import com.example.vocabs_kmm.study.random.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.study.all.domain.GetAllFlashcardsInCertainLanguage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object StudyUseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetRandomFlashCardUseCase(dataSource: FlashcardDataSource): GetRandomFlashcard {
        return GetRandomFlashcard(flashcardDataSource = dataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideGetAllFlashCardsOfCertainLanguageUseCase(dataSource: FlashcardDataSource): GetAllFlashcardsInCertainLanguage {
        return GetAllFlashcardsInCertainLanguage(flashcardDataSource = dataSource)
    }

}