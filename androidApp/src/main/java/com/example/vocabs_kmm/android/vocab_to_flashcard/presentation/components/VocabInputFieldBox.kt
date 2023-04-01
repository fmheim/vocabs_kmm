package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardEvent
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun VocabInputFieldBox(
    state: VocabToFlashcardState,
    modifier: Modifier = Modifier,
    onEvent: (VocabToFlashcardEvent) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(modifier = modifier, contentAlignment = Alignment.Center){
        val customTextSelectionColors = TextSelectionColors(
            handleColor = MaterialTheme.colors.surface.copy(alpha = 0.8f),
            backgroundColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.3f),
        )

        CompositionLocalProvider(
            LocalTextSelectionColors provides customTextSelectionColors,
        ){
            OutlinedTextField(
                singleLine = true,
                shape = CircleShape,
                placeholder = {Text(text = "type a word...", textAlign = TextAlign.Center)},
                value = state.vocabInput ?: "",
                textStyle = MaterialTheme.typography.h2,
                onValueChange = { newValue -> onEvent(VocabToFlashcardEvent.VocabInputChanged(text = newValue)) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onEvent(VocabToFlashcardEvent.GeneratePhrase)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.onPrimary,
                    unfocusedBorderColor = MaterialTheme.colors.onPrimary,
                    textColor = MaterialTheme.colors.onPrimary,
                    backgroundColor = MaterialTheme.colors.primary,
                    placeholderColor = MaterialTheme.colors.onPrimary.copy(alpha = 0.7f),
                    cursorColor = MaterialTheme.colors.onPrimary.copy(alpha =0.5f)
                )
            )
        }

    }
}

@Preview
@Composable
fun VocabInputFieldPreview() {
    VocabsTheme {
        VocabInputFieldBox(onEvent = {}, state = VocabToFlashcardState())
    }
}