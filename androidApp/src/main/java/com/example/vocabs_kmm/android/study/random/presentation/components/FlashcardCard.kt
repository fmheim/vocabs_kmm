package com.example.vocabs_kmm.android.study.random.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.android.core.presentation.components.AsyncImageBox
import com.example.vocabs_kmm.android.core.presentation.theme.LightGreen
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.study.random.presentation.StudyRandomState

@Composable
fun FlashcardCard(
    flashcard: Flashcard,
    isShowingAnswer: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(23),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier,
        border = BorderStroke(width = 3.dp, color = MaterialTheme.colors.surface)
    ) {
        Column(
            modifier = Modifier
                .heightIn(min = 200.dp, max = 450.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            AsyncImageBox(image = flashcard.image, onSuccess = {})

            Text(
                modifier = Modifier.padding(all = 16.dp),
                text = buildAnnotatedString {
                    append(flashcard.beforeVocabText)
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold, color = LightGreen
                        )
                    ) {
                        if (isShowingAnswer) append(flashcard.vocabInPhrase) else append(
                            "[...]"
                        )
                    }
                    append(flashcard.afterVocabText)
                },
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
private fun FlashcardComponentPreview() {
    VocabsTheme {
        FlashcardCard(
            flashcard = Flashcard(
                id = null,
                languageCode = "en",
                vocab = "Hello",
                beforeVocabText = "The word ",
                afterVocabText = ", is a common greeting.",
                vocabInPhrase = "hello",
                image = byteArrayOf()
            ), isShowingAnswer = false
        )
    }
}