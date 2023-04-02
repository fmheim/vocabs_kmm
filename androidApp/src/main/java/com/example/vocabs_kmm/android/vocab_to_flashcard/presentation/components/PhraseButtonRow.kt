package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.DarkPurple
import com.example.vocabs_kmm.android.core.presentation.theme.LightGreen
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.android.core.presentation.widgets.LanguageDropDown
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardEvent
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardState

@Composable
fun BoxScope.PhraseButtonsRow(
    onEvent: (VocabToFlashcardEvent) -> Unit, state: VocabToFlashcardState
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ReDoIconButton(onEvent, state)
        LanguageIconButton(onEvent, state)
        SaveIconButton(onEvent, state)

    }
    LanguageDropDown(
        isExpanded = state.isChoosingLanguage,
        onLanguageSelected = { language -> onEvent(VocabToFlashcardEvent.SelectLanguage(language) )},
        close = {onEvent(VocabToFlashcardEvent.CloseLanguageDropDown)})
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SaveIconButton(onEvent: (VocabToFlashcardEvent) -> Unit, state: VocabToFlashcardState) {
    IconButton(
        onClick = { onEvent(VocabToFlashcardEvent.SaveFlashcard) },
        modifier = Modifier
            .background(
                color = if (state.phrase == null) MaterialTheme.colors.primary.copy(alpha = 0.5f) else if (state.showSavedFlashcard) DarkPurple else MaterialTheme.colors.primary,
                shape = CircleShape
            )
            .border(
                shape = CircleShape,
                width = 1.dp,
                color = if(state.showSavedFlashcard) LightGreen else if (isSystemInDarkTheme()) Color.Transparent else MaterialTheme.colors.surface
            )
            .padding(7.dp),
        enabled = !(state.phrase == null || state.isGeneratingImage)
    ) {
        AnimatedContent(
            targetState = state.showSavedFlashcard,
            label = "saved animation"
        ) { showSaved ->
            if (showSaved) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.saved_success),
                    modifier = Modifier.size(45.dp),
                    tint = LightGreen
                )
            } else
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(R.string.save),
                    modifier = Modifier.size(45.dp),
                    tint = if (state.phrase == null) MaterialTheme.colors.onPrimary.copy(alpha = 0.5f) else MaterialTheme.colors.onPrimary
                )
        }
    }
}

@Composable
private fun RowScope.LanguageIconButton(
    onEvent: (VocabToFlashcardEvent) -> Unit, state: VocabToFlashcardState
) {
    IconButton(
        onClick = {
            if (state.isChoosingLanguage) onEvent(VocabToFlashcardEvent.CloseLanguageDropDown) else onEvent(
                VocabToFlashcardEvent.OpenLanguageDropDown
            )
        }, modifier = Modifier
            .background(
                color = Color.Transparent, shape = CircleShape
            )
            .align(Alignment.Bottom)
            .padding(top = 30.dp)
            .border(width = 3.dp, color = MaterialTheme.colors.primary, shape = CircleShape)

    ) {
        AsyncImage(
            model = state.selectedLanguage.drawableRes,
            contentDescription = "selectedLanguage",
            modifier = Modifier.size(60.dp),
        )
    }
}

@Composable
private fun ReDoIconButton(onEvent: (VocabToFlashcardEvent) -> Unit, state: VocabToFlashcardState) {
    IconButton(
        onClick = { onEvent(VocabToFlashcardEvent.GeneratePhrase) }, modifier = Modifier
            .background(
                color = if (state.phrase == null || state.isGeneratingImage) MaterialTheme.colors.primary.copy(alpha = 0.5f) else MaterialTheme.colors.primary,
                shape = CircleShape
            )
            .border(
                shape = CircleShape,
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Color.Transparent else MaterialTheme.colors.surface
            )
            .padding(7.dp),
        enabled = !(state.phrase == null || state.isGeneratingImage)
    ) {
        Icon(
            imageVector = Icons.Default.Autorenew,
            contentDescription = stringResource(R.string.re_generate),
            modifier = Modifier.size(45.dp),
            tint = if (state.phrase == null || state.isGeneratingImage) MaterialTheme.colors.onPrimary.copy(alpha = 0.5f) else MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun PhraseButtonsRowPreview() {
    VocabsTheme(darkTheme = false) {
        Box() {
            PhraseButtonsRow(onEvent = {}, state = VocabToFlashcardState(isChoosingLanguage = true))
        }
    }
}