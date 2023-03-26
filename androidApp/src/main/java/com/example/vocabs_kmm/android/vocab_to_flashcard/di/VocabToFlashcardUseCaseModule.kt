package com.example.vocabs_kmm.android.vocab_to_flashcard.di

import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard.SaveAsFlashcard
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhrase
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object VocabToFlashcardUseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideVocabToPhraseUseCase(
        client: VocabToPhraseClient
    ): VocabToPhrase {
        return VocabToPhrase(client = client)
    }

    @Provides
    @ViewModelScoped
    fun providerSaveAsFlashcardUseCase(
        dataSource: FlashcardDataSource
    ):SaveAsFlashcard{
        return SaveAsFlashcard(flashcardDataSource = dataSource)
    }
}