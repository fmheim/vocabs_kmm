package com.example.vocabs_kmm.android.study.presentation.components

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
import com.example.vocabs_kmm.android.core.presentation.theme.LightGreen
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.core.domain.flashcard.Flashcard
import com.example.vocabs_kmm.study.presentation.StudyState

@Composable
fun FlashcardCard(
    flashcard: Flashcard,
    state: StudyState
) {
    Card(
        shape = RoundedCornerShape(37),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.padding(all = 24.dp)

    ) {
        Column(
            modifier = Modifier
                .heightIn(min = 200.dp)
                .padding(all = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append(flashcard.beforeVocabText)
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold, color = LightGreen
                        )
                    ) {
                        if (state.isShowingAnswer) append(flashcard.vocabInPhrase) else append(
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
                vocabInPhrase = "hello"
            ), state = StudyState(isShowingAnswer = false)
        )
    }
}