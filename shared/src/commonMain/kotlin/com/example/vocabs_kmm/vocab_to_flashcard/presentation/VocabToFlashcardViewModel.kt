package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import com.example.vocabs_kmm.core.domain.flashcard.FlashcardException
import com.example.vocabs_kmm.core.domain.util.Resource
import com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard.SaveAsFlashcard
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.GenerateImage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.ImageGenerationException
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescription
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VocabToFlashcardViewModel(
    coroutineScope: CoroutineScope?,
    private val vocabToPhraseWithImageDescription: VocabToPhraseWithImageDescription,
    private val saveAsFlashcard: SaveAsFlashcard,
    private val generateImage: GenerateImage
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    private val _state = MutableStateFlow(VocabToFlashcardState())
    val state = _state.asStateFlow()


    private var phraseGenerateJob: Job? = null
    private var imageGenerateJob: Job? = null

    fun onEvent(event: VocabToFlashcardEvent) {
        when (event) {
            VocabToFlashcardEvent.GenerateImage         -> state.value.imageDescription?.let {
                generateImage(
                    it
                )
            }

            is VocabToFlashcardEvent.GeneratePhrase     -> generatePhraseWithImageDescription(
                currentState = state.value.copy()
            )

            VocabToFlashcardEvent.OnErrorSeen              -> _state.update { it.copy(error = null) }
            is VocabToFlashcardEvent.ImageDownloadFinished -> _state.update { it.copy(image = event.loadedImage, imageDescription = null) }
            VocabToFlashcardEvent.SaveFlashcard            -> saveAsFlashcardToDb(currentState = state.value.copy())
            is VocabToFlashcardEvent.VocabInputChanged  -> _state.update { it.copy(vocabInput = event.text) }
            is VocabToFlashcardEvent.SelectLanguage     -> _state.update {
                it.copy(
                    selectedLanguage = event.language, phrase = null
                )
            }

            VocabToFlashcardEvent.OpenLanguageDropDown  -> _state.update {
                it.copy(
                    isChoosingLanguage = true
                )
            }

            VocabToFlashcardEvent.CloseLanguageDropDown -> _state.update {
                it.copy(
                    isChoosingLanguage = false
                )
            }

            VocabToFlashcardEvent.ToStudyScreen         -> Unit //handled in navigation

        }
    }

    private fun saveAsFlashcardToDb(currentState: VocabToFlashcardState) {
        viewModelScope.launch {
            val result = saveAsFlashcard.execute(
                examplePhrase = currentState.phrase,
                languageCode = currentState.selectedLanguage.language.langCode,
                vocab = currentState.vocabInput,
                image = currentState.image
            )
            when (result) {
                is Resource.Error   -> _state.update { it.copy(error = result.throwable as? FlashcardException) }
                is Resource.Success -> _state.update {
                    it.copy(
                        lastSavedFlashCard = result.data, showThatHasSavedFlashcard = true
                    )
                }
            }
            delay(1200)
            _state.update { it.copy(showThatHasSavedFlashcard = false) }
        }
    }

    private fun generatePhraseWithImageDescription(currentState: VocabToFlashcardState) {
        if (phraseGenerateJob?.isActive == true) return
        phraseGenerateJob = viewModelScope.launch {
            _state.update { it.copy(isGeneratingText = true, submittedVocab = it.vocabInput) }
            when (val result = vocabToPhraseWithImageDescription.execute(
                languageName = currentState.selectedLanguage.language.langName,
                vocab = currentState.vocabInput
            )) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            phrase = result.data?.examplePhrase,
                            isGeneratingText = false,
                            imageDescription = result.data?.imageDescription
                        )
                    }
                }

                is Resource.Error   -> {
                    _state.update {
                        it.copy(
                            phrase = null,
                            isGeneratingText = false,
                            error = (result.throwable as? VocabToPhraseException)
                        )
                    }
                }
            }
        }
    }


    private fun generateImage(prompt: String) {
        if (imageGenerateJob?.isActive == true) return
        //_state.update { it.copy(imageUrl = "https://oaidalleapiprodscus.blob.core.windows.net/private/org-qzCQIoaScicRExxMIXI4iV8c/user-Nnd1OQVYfQdrsyMBeGUDT8Pv/img-X6Rz0Mce6QeiwM86xYW9Pafr.png?st=2023-04-02T10%3A24%3A09Z&se=2023-04-02T12%3A24%3A09Z&sp=r&sv=2021-08-06&sr=b&rscd=inline&rsct=image/png&skoid=6aaadede-4fb3-4698-a8f6-684d7786b067&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2023-04-02T06%3A27%3A46Z&ske=2023-04-03T06%3A27%3A46Z&sks=b&skv=2021-08-06&sig=WF9wf44Zcy8bkjz6wpj6QuQQRSR/Uk9NIXVglYH6Fmg%3D" ) }
        imageGenerateJob = viewModelScope.launch {
            _state.update { it.copy(isGeneratingImage = true) }
            when (val result = generateImage.execute(prompt)) {
                is Resource.Error   -> _state.update {
                    it.copy(
                        error = result.throwable as? ImageGenerationException,
                        isGeneratingImage = false,
                        imageUrl = null
                    )
                }

                is Resource.Success -> _state.update {
                    it.copy(
                        imageUrl = result.data, isGeneratingImage = false
                    )
                }
            }
        }
    }


}