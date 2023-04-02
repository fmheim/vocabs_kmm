package com.example.vocabs_kmm.android.vocab_to_flashcard.di

import com.example.vocabs_kmm.core.domain.flashcard.FlashcardDataSource
import com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard.SaveAsFlashcard
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.GenerateImage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescription
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescriptionClient
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
        client: VocabToPhraseWithImageDescriptionClient
    ): VocabToPhraseWithImageDescription {
        return VocabToPhraseWithImageDescription(client = client)
    }

    @Provides
    @ViewModelScoped
    fun provideSaveAsFlashcardUseCase(
        dataSource: FlashcardDataSource
    ):SaveAsFlashcard{
        return SaveAsFlashcard(flashcardDataSource = dataSource)
    }

    @Provides
    @ViewModelScoped
    fun provideGenerateImageUseCase(
        client: ImageGenerationClient
    ):GenerateImage{
        return GenerateImage(client = client)
    }
}