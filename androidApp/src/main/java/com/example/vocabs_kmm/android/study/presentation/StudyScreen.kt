package com.example.vocabs_kmm.android.study.presentation

import android.view.Surface
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NextPlan
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.study.presentation.StudyEvent
import com.example.study.presentation.StudyState
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.LightGreen
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme
import com.example.vocabs_kmm.core.presentation.UiLanguage


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StudyScreen(state: StudyState, onEvent: (StudyEvent) -> Unit) {
    Scaffold(backgroundColor = MaterialTheme.colors.background, floatingActionButton = {
        FloatingActionButton(onClick = {
            if (state.isChoosingLanguage) onEvent(StudyEvent.CloseLanguageDropDown) else onEvent(
                StudyEvent.OpenLanguageDropDown
            )
        }, backgroundColor = MaterialTheme.colors.primary) {

            Image(
                painter = painterResource(id = state.selectedLanguage.drawableRes),
                contentDescription = "selectedLanguage",
                modifier = Modifier.size(60.dp),
            )
        }
    }) { paddingValues ->
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

                        Card(
                            shape = RoundedCornerShape(37),
                            backgroundColor = MaterialTheme.colors.surface,
                            modifier = Modifier.padding(all = 24.dp)

                        ) {
                            Column(
                                modifier = Modifier.heightIn(min = 200.dp).padding(all = 16.dp),
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
                        Spacer(modifier = Modifier.height(16.dp))
                        if (state.isShowingAnswer) {
                            IconButton(
                                onClick = { onEvent(StudyEvent.ShowNextCard) },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.primary,
                                        shape = CircleShape
                                    )
                                    .border(
                                        shape = CircleShape,
                                        width = 1.dp,
                                        color = if (isSystemInDarkTheme()) Color.Transparent else MaterialTheme.colors.surface
                                    )
                                    .padding(7.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.NextPlan,
                                    contentDescription = "next card",
                                    modifier = Modifier.size(45.dp),
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { onEvent(StudyEvent.ShowAnswer) },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.primary,
                                        shape = CircleShape
                                    )
                                    .border(
                                        shape = CircleShape,
                                        width = 1.dp,
                                        color = if (isSystemInDarkTheme()) Color.Transparent else MaterialTheme.colors.surface
                                    )
                                    .padding(7.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = stringResource(
                                        R.string.show_answer
                                    ),
                                    tint = MaterialTheme.colors.onPrimary,
                                    modifier = Modifier.size(45.dp)
                                )

                            }
                        }


                    } else {
                        OutlinedButton(
                            onClick = { onEvent(StudyEvent.ShowNextCard) },
                            shape = CircleShape,
                            colors = ButtonDefaults.outlinedButtonColors(
                                backgroundColor = MaterialTheme.colors.background,
                                contentColor = LightGreen
                            ),
                            border = BorderStroke(width = 1.dp, color = LightGreen)
                        ) {
                            Text(text = "Study random flash card", style = MaterialTheme.typography.button)
                        }
                    }
                }

            }


        }
        DropdownMenu(modifier = Modifier.background(
            Brush.horizontalGradient(
                colors = listOf(
                    MaterialTheme.colors.surface, MaterialTheme.colors.background
                )
            )
        ),
            expanded = state.isChoosingLanguage,
            onDismissRequest = { onEvent(StudyEvent.CloseLanguageDropDown) }) {
            UiLanguage.allLanguages.forEach {
                DropdownMenuItem(onClick = {
                    onEvent(StudyEvent.SelectLanguage(it))
                    onEvent(StudyEvent.CloseLanguageDropDown)
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


}

@Preview
@Composable
fun StudyScreenPreview() {
    VocabsTheme(darkTheme = true) {
        StudyScreen(state = StudyState(), onEvent = {})
    }
}