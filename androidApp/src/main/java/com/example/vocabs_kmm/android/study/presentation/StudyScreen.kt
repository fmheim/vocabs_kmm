package com.example.vocabs_kmm.android.study.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.android.core.presentation.widgets.LanguageDropDown
import com.example.vocabs_kmm.android.study.presentation.components.FlashcardCard
import com.example.vocabs_kmm.android.study.presentation.components.NextCardIconButton
import com.example.vocabs_kmm.android.study.presentation.components.ShowAnswerIconButton
import com.example.vocabs_kmm.android.study.presentation.components.StudyRandomButton
import com.example.vocabs_kmm.study.domain.flashcard.FlashcardError
import com.example.vocabs_kmm.study.presentation.StudyEvent
import com.example.vocabs_kmm.study.presentation.StudyState


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StudyScreen(state: StudyState, onEvent: (StudyEvent) -> Unit) {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(all = 16.dp)
                    .clickable { onEvent(StudyEvent.BackClick) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (state.isChoosingLanguage) onEvent(StudyEvent.CloseLanguageDropDown) else onEvent(
                    StudyEvent.OpenLanguageDropDown
                )
            }, backgroundColor = MaterialTheme.colors.primary) {

                AsyncImage(
                    model = state.selectedLanguage.drawableRes,
                    contentDescription = "selectedLanguage",
                    modifier = Modifier.size(60.dp),
                )
            }
        }) { paddingValues ->

        val context = LocalContext.current
        LaunchedEffect(key1 = state.error) {
            state.error?.let {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = when (it) {
                        FlashcardError.N0_FLASHCARD_FOUND -> context.getString(
                            R.string.no_flashcards_saved_yet,
                            state.selectedLanguage.language.langName
                        )

                        FlashcardError.UNKNOWN_ERROR      -> context.getString(R.string.an_unknown_error_occurred_please_try_again)
                    }
                )
                onEvent(StudyEvent.OnErrorSeen)

            }

        }
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedContent(targetState = state.currentFlashCard, label = "") { flashcard ->
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (flashcard != null) {

                        FlashcardCard(flashcard, state)
                        Spacer(modifier = Modifier.height(16.dp))
                        if (state.isShowingAnswer) NextCardIconButton(onEvent)
                        else ShowAnswerIconButton(onEvent)

                    } else {

                        StudyRandomButton(onEvent)

                    }
                }

            }
        }


        LanguageDropDown(
            isExpanded = state.isChoosingLanguage,
            onLanguageSelected = { language -> onEvent(StudyEvent.SelectLanguage(language) )},
            close = {onEvent(StudyEvent.CloseLanguageDropDown)})
    }
}




@Preview
@Composable
fun StudyScreenPreview() {
    VocabsTheme(darkTheme = true) {
        StudyScreen(state = StudyState(), onEvent = {})
    }
}