package com.example.vocabs_kmm.android.study.di

import com.example.vocabs_kmm.study.domain.flashcard.GetRandomFlashcard
import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
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

}