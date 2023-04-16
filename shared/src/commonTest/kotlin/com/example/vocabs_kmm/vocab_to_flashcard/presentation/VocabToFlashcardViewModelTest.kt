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
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

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
    fun `test that state is updated correctly on GeneratePhrase and GenerateImage event`()= runBlocking{
        val vocabInput = "house"

        viewModel.state.test {
            awaitItem()

            viewModel.onEvent(event = VocabToFlashcardEvent.VocabInputChanged(text = vocabInput))
            awaitItem()
            viewModel.onEvent(event = VocabToFlashcardEvent.GeneratePhrase)

            val loadingPhraseState = awaitItem()
            assertThat(loadingPhraseState.isGeneratingText).isTrue()
            assertThat(loadingPhraseState.submittedVocab).isEqualTo(vocabInput)

            val phraseResultState = awaitItem()
            assertThat(phraseResultState.isGeneratingText).isFalse()
            assertThat(phraseResultState.phrase).isEqualTo(ExamplePhrase(beforeVocab = "The green ", vocab = "house", afterVocab = " was beautiful. "))
            assertThat(phraseResultState.imageDescription).isEqualTo("A realistic image of a beautiful green house with a blue sky and green grass in the background.")

            viewModel.onEvent(event = VocabToFlashcardEvent.GenerateImage)
            val imageLoadingState = awaitItem()
            assertThat(imageLoadingState.isGeneratingImage).isTrue()

            val imageResultState = awaitItem()
            assertThat(imageResultState.isGeneratingImage).isFalse()
            assertThat(imageResultState.imageUrl).isEqualTo(fakeImageGenerationClient.imageGenerationResponse.data.first().url)
        }
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `test that state is updated correctly when flashcard is saved`() = runBlocking {


        viewModel.state.test(timeout = 2000.milliseconds) {
            awaitItem()

            viewModel.onEvent(event = VocabToFlashcardEvent.VocabInputChanged(text = "house"))
            awaitItem()
            viewModel.onEvent(event = VocabToFlashcardEvent.GeneratePhrase)
            awaitItem()
            awaitItem()
            viewModel.onEvent(event = VocabToFlashcardEvent.GenerateImage)
            awaitItem()
            awaitItem()
            viewModel.onEvent(event = VocabToFlashcardEvent.ImageDownloadFinished(loadedImage = byteArrayOf()))
            awaitItem()

            viewModel.onEvent(event = VocabToFlashcardEvent.SaveFlashcard)
            val hasSavedFlashcardState = awaitItem()
            assertThat(hasSavedFlashcardState.showThatHasSavedFlashcard).isTrue()
            assertThat (hasSavedFlashcardState.lastSavedFlashCard).isEqualTo(fakeFlashcardDataSource.lastInsertedFlashcard)

            val afterSavedState = awaitItem()
            assertThat(afterSavedState.showThatHasSavedFlashcard).isFalse()

        }
    }
}
