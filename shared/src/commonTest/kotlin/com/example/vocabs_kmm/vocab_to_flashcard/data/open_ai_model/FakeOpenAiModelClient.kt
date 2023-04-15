package com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model

import com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model.dto.OpenAiModelInfo
import com.example.vocabs_kmm.vocab_to_flashcard.data.open_ai_model.dto.OpenAiModelList
import com.example.vocabs_kmm.vocab_to_flashcard.domain.open_ai_model.OpenAiModelClient

class FakeOpenAiModelClient: OpenAiModelClient {
    var openAiModelList = OpenAiModelList(models = listOf(OpenAiModelInfo(modelId = "123", typeOfObject = "model", modelOwner = "you", created = 1681564483)), typeOfObject = "list")
    override suspend fun getModels(): OpenAiModelList {
        return  openAiModelList
    }

    override suspend fun getModelInfo(): OpenAiModelInfo {
        return openAiModelList.models?.first() ?: OpenAiModelInfo()
    }
}