package com.example.vocabs_kmm.android.study.all.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.widgets.LanguageDropDown
import com.example.vocabs_kmm.android.study.random.presentation.components.FlashcardCard
import com.example.vocabs_kmm.study.all.presentation.StudyAllEvent
import com.example.vocabs_kmm.study.all.presentation.StudyAllState

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StudyAllScreen(state: StudyAllState, onEvent: (StudyAllEvent) -> Unit) {

    LaunchedEffect(key1 = null){
        onEvent(StudyAllEvent.LoadFlashCards(languageCode = state.selectedLanguage.language.langCode))
    }

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(all = 16.dp)
                    .clickable { onEvent(StudyAllEvent.BackClick) }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (state.isChoosingLanguage) onEvent(StudyAllEvent.CloseLanguageDropDown) else onEvent(
                    StudyAllEvent.OpenLanguageDropDown
                )
            }, backgroundColor = MaterialTheme.colors.primary) {

                AsyncImage(
                    model = state.selectedLanguage.drawableRes,
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
            AnimatedContent(targetState = state.isLoadingFlashcards, label = "") { isLoading ->
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    LazyColumn(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        state.currentFlashCards?.forEach { flashcard ->
                            item {

                                    FlashcardCard(modifier = Modifier.padding(horizontal = 24.dp).padding(bottom = 24.dp),flashcard = flashcard, isShowingAnswer = true)



                            }
                        }
                    }


                }
            }


            LanguageDropDown(
                isExpanded = state.isChoosingLanguage,
                onLanguageSelected = { language -> onEvent(StudyAllEvent.SelectLanguage(language)) },
                close = { onEvent(StudyAllEvent.CloseLanguageDropDown) })
        }
    }
}