package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.core.presentation.UiLanguage
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
    DropdownMenu(modifier = Modifier.background(
        Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colors.surface, MaterialTheme.colors.background
            )
        )
    ),
        expanded = state.isChoosingLanguage,
        onDismissRequest = { onEvent(VocabToFlashcardEvent.CloseLanguageDropDown) }) {
        UiLanguage.allLanguages.forEach {
            DropdownMenuItem(onClick = {
                onEvent(VocabToFlashcardEvent.SelectLanguage(it))
                onEvent(VocabToFlashcardEvent.CloseLanguageDropDown)
            }) {
                Image(
                    painterResource(id = it.drawableRes),
                    contentDescription = it.language.langName,
                    modifier = Modifier.size(30.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = it.language.langName)
            }


        }
    }
}

@Composable
private fun SaveIconButton(onEvent: (VocabToFlashcardEvent) -> Unit, state: VocabToFlashcardState) {
    IconButton(
        onClick = { onEvent(VocabToFlashcardEvent.SaveFlashcard) },
        modifier = Modifier
            .background(
                color = if (state.phrase == null) MaterialTheme.colors.primary.copy(alpha = 0.5f) else MaterialTheme.colors.primary,
                shape = CircleShape
            )
            .padding(7.dp),
        enabled = state.phrase != null
    ) {
        Icon(
            imageVector = Icons.Default.Save,
            contentDescription = stringResource(R.string.save),
            modifier = Modifier.size(45.dp),
            tint = if (state.phrase == null) MaterialTheme.colors.onPrimary.copy(alpha = 0.5f) else MaterialTheme.colors.onPrimary
        )
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
        Image(
            painter = painterResource(id = state.language.drawableRes),
            contentDescription = "language",
            modifier = Modifier.size(60.dp),
        )
    }
}

@Composable
private fun ReDoIconButton(onEvent: (VocabToFlashcardEvent) -> Unit, state: VocabToFlashcardState) {
    IconButton(
        onClick = { onEvent(VocabToFlashcardEvent.GeneratePhrase) }, modifier = Modifier
            .background(
                color = if (state.phrase == null) MaterialTheme.colors.primary.copy(alpha = 0.5f) else MaterialTheme.colors.primary,
                shape = CircleShape
            )
            .padding(7.dp),
        enabled = state.phrase != null
    ) {
        Icon(
            imageVector = Icons.Default.Autorenew,
            contentDescription = stringResource(R.string.re_generate),
            modifier = Modifier.size(45.dp),
            tint = if (state.phrase == null) MaterialTheme.colors.onPrimary.copy(alpha = 0.5f) else MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun PhraseButtonsRowPreview() {
    VocabsTheme {
        Box() {
            PhraseButtonsRow(onEvent = {}, state = VocabToFlashcardState(isChoosingLanguage = true))
        }
    }
}