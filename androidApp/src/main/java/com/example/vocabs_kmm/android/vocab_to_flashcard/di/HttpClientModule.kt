package com.example.vocabs_kmm.android.vocab_to_flashcard.di

import com.example.vocabs_kmm.vocab_to_flashcard.data.remote.OpenAiHttpClientFactory
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.KtorVocabToPhraseClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseClient
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
    fun provideVocabToPhraseClient(httpClient: HttpClient): VocabToPhraseClient {
        return KtorVocabToPhraseClient(openAiHttpClient = httpClient )
    }
}