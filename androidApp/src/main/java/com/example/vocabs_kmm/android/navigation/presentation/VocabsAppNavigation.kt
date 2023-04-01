package com.example.vocabs_kmm.android.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.vocabs_kmm.study.presentation.StudyEvent
import com.example.vocabs_kmm.android.study.presentation.AndroidStudyViewModel
import com.example.vocabs_kmm.android.study.presentation.StudyScreen
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.AndroidVocabToFlashCardViewModel
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.VocabToFlashcardScreen
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.vocab_to_flashcard.presentation.VocabToFlashcardEvent

@Composable
fun VocabsAppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.VocabToFlashcardScreen,
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Routes.VocabToFlashcardScreen) {
            val viewModel: AndroidVocabToFlashCardViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            VocabToFlashcardScreen(state = state, onEvent = { event ->
                when (event) {
                    is VocabToFlashcardEvent.ToStudyScreen -> navController.navigate(route = Routes.StudyScreen + "/" + state.selectedLanguage.language.langCode)
                    else                                   -> viewModel.onEvent(event)
                }
            })
        }

        composable(
            route = Routes.StudyScreen + "/{languageCode}",
            arguments = listOf(navArgument(name = "languageCode") {
                type = NavType.StringType
                defaultValue = "en"
            })
        ) { backstackEntry ->
            val viewModel: AndroidStudyViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            LaunchedEffect(key1 = null) {
                viewModel.onEvent(
                    StudyEvent.SelectLanguage(
                        UiLanguage.byCode(
                            backstackEntry.arguments?.getString("languageCode") ?: "en"
                        )
                    )
                )
            }
            StudyScreen(state = state, onEvent = { event ->
                when (event) {
                    is StudyEvent.BackClick -> navController.popBackStack()
                    else                    -> viewModel.onEvent(event)
                }
            })
        }
    }
}