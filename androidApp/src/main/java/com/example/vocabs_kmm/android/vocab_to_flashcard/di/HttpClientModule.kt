package com.example.vocabs_kmm.android.vocab_to_flashcard.di

import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.KtorImageGenerationClient
import com.example.vocabs_kmm.vocab_to_flashcard.data.remote.OpenAiHttpClientFactory
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.KtorVocabToPhraseWithImageDescriptionClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescriptionClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient


@Module
@InstallIn(ViewModelComponent::class)
class HttpClientModule {
    @Provides
    @ViewModelScoped
    fun provideHttpClient(): HttpClient {
        return OpenAiHttpClientFactory().create()
    }


    @Provides
    @ViewModelScoped
    fun provideVocabToPhraseClient(httpClient: HttpClient): VocabToPhraseWithImageDescriptionClient {
        return KtorVocabToPhraseWithImageDescriptionClient(openAiHttpClient = httpClient )
    }

    @Provides
    @ViewModelScoped
    fun provideImageGenerationClient(httpClient: HttpClient): ImageGenerationClient {
        return KtorImageGenerationClient(openAiHttpClient = httpClient )
    }


}