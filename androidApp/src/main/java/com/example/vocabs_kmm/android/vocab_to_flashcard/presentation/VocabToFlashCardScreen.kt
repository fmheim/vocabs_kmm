package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.drawable.toBitmap
import coil.compose.AsyncImage
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.components.AsyncImageBox
import com.example.vocabs_kmm.android.core.presentation.theme.LightGreen
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components.AnimatedImageLoadingIcon
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components.ExamplePhraseComponent
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components.VocabInputFieldBox
import com.example.vocabs_kmm.vocab_to_flashcard.domain.vocab_to_phrase.ExamplePhrase
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardEvent
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardState
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VocabToFlashcardScreen(state: VocabToFlashcardState, onEvent: (VocabToFlashcardEvent) -> Unit) {
    LaunchedEffect(key1 = state.imageDescription) {
        if (state.imageDescription.isNullOrBlank()) return@LaunchedEffect
        onEvent(VocabToFlashcardEvent.GenerateImage)
    }




    Scaffold(
        backgroundColor = MaterialTheme.colors.background, floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(VocabToFlashcardEvent.ToStudyScreen) },
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = LightGreen,
                shape = RoundedCornerShape(37)
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = stringResource(R.string.study)
                )

            }
        }, floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            AnimatedContent(
                targetState = state.isGeneratingImage, label = "generated image"
            ) { isGenerating ->
                if (isGenerating) {
                    AnimatedImageLoadingIcon()
                } else {
                    if (state.imageUrl == null) {
                        AsyncImage(
                            model = R.drawable.img,
                            contentDescription = stringResource(R.string.place_holder_image),
                            modifier = Modifier.fillMaxHeight(0.25f),
                            contentScale = ContentScale.FillWidth
                        )
                    } else {

                        AsyncImageBox(image = state.imageUrl, onSuccess = { coilState ->
                            val bitmap = coilState.result.drawable.toBitmap()
                            val stream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                            onEvent(
                                VocabToFlashcardEvent.ImageDownloadFinished(
                                    stream.toByteArray()
                                )
                            )
                        })

                    }

                }

            }

            ExamplePhraseComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.41f),
                onEvent = onEvent,
                state = state
            )

            VocabInputFieldBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
                onEvent = onEvent,
                state = state
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun VocabToFlashcardScreenPreview() {
    VocabsTheme(darkTheme = false) {
        VocabToFlashcardScreen(state = VocabToFlashcardState(
            phrase = ExamplePhrase(
                beforeVocab = "This is an ",
                vocab = "example",
                afterVocab = " that explains a word and was generated by ai."
            )
        ), onEvent = {})
    }
}


