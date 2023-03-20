package com.example.vocabs_kmm.vocab_to_phrase.domain.open_ai_model

import com.example.vocabs_kmm.vocab_to_phrase.data.open_ai_model.dto.OpenAiModelInfo
import com.example.vocabs_kmm.vocab_to_phrase.data.open_ai_model.dto.OpenAiModelList

interface OpenAiModelClient {
    suspend fun getModels(): OpenAiModelList
    suspend fun getModelInfo(): OpenAiModelInfo

}