package com.example.vocabs_kmm.vocab_to_flashcard.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.example.vocabs_kmm.core.data.flashcard.FakeFlashcardDataSource
import com.example.vocabs_kmm.vocab_to_flashcard.data.image_generation.FakeImageGenerationClient
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.FakeVocabToPhraseWithImageDescriptionClient
import com.example.vocabs_kmm.vocab_to_flashcard.domain.flashcard.SaveAsFlashcard
import com.example.vocabs_kmm.vocab_to_flashcard.domain.image_generation.GenerateImage
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.ExamplePhrase
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.VocabToPhraseWithImageDescription
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test

class VocabToFlashcardViewModelTest {

    private lateinit var viewModel: VocabToFlashcardViewModel
    private lateinit var fakeVocabToPhraseWithImageDescriptionClient: FakeVocabToPhraseWithImageDescriptionClient
    private lateinit var fakeImageGenerationClient: FakeImageGenerationClient
    private lateinit var fakeFlashcardDataSource: FakeFlashcardDataSource

    @BeforeTest
    fun setup() {
        fakeVocabToPhraseWithImageDescriptionClient = FakeVocabToPhraseWithImageDescriptionClient()
        fakeImageGenerationClient = FakeImageGenerationClient()
        fakeFlashcardDataSource = FakeFlashcardDataSource()
        val generateImage = GenerateImage(client = fakeImageGenerationClient)
        val vocabToPhraseWithImageDescription =
            VocabToPhraseWithImageDescription(client = fakeVocabToPhraseWithImageDescriptionClient)
        val saveAsFlashcard = SaveAsFlashcard(flashcardDataSource = fakeFlashcardDataSource)

        viewModel = VocabToFlashcardViewModel(
            vocabToPhraseWithImageDescription = vocabToPhraseWithImageDescription,
            generateImage = generateImage,
            saveAsFlashcard = saveAsFlashcard,
            coroutineScope = CoroutineScope(Dispatchers.Default)
        )
    }

    @Test
    fun `test that state is updated correctly when GeneratePhrase event is triggered`()= runBlocking{
        val vocabInput = "house"


        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(event = VocabToFlashcardEvent.VocabInputChanged(text = vocabInput))
            awaitItem()
            viewModel.onEvent(event = VocabToFlashcardEvent.GeneratePhrase)

            val loadingState = awaitItem()
            assertThat(loadingState.isGeneratingText).isTrue()
            assertThat(loadingState.submittedVocab).isEqualTo(vocabInput)

            val resultState = awaitItem()
            assertThat(resultState.isGeneratingText).isFalse()
            assertThat(resultState.phrase).isEqualTo(ExamplePhrase(beforeVocab = "The green ", vocab = "house", afterVocab = " was beautiful. "))
            assertThat(resultState.imageDescription).isEqualTo("A realistic image of a beautiful green house with a blue sky and green grass in the background.")


        }


    }
}
