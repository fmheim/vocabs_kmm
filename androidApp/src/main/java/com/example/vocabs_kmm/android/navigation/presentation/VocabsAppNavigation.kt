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
import com.example.vocabs_kmm.android.study.all.presentation.AndroidStudyAllViewModel
import com.example.vocabs_kmm.android.study.all.presentation.StudyAllScreen
import com.example.vocabs_kmm.android.study.menu.presentation.StudyMenuScreen
import com.example.vocabs_kmm.study.random.presentation.StudyRandomEvent
import com.example.vocabs_kmm.android.study.random.presentation.AndroidStudyRandomViewModel
import com.example.vocabs_kmm.android.study.random.presentation.StudyScreen
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.AndroidVocabToFlashCardViewModel
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.VocabToFlashcardScreen
import com.example.vocabs_kmm.core.presentation.UiLanguage
import com.example.vocabs_kmm.study.all.presentation.StudyAllEvent
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
                    is VocabToFlashcardEvent.ToStudyScreen -> navController.navigate(route = Routes.StudyMenuScreen + "/" + state.selectedLanguage.language.langCode)
                    else                                   -> viewModel.onEvent(event)
                }
            })
        }

        composable(
            route = Routes.StudyMenuScreen + "/{languageCode}",
            arguments = listOf(navArgument(name = "languageCode") {
                type = NavType.StringType
                defaultValue = "en"
            })
        ) { backstackEntry ->
            StudyMenuScreen(onToRandom = {
                navController.navigate(
                    route = Routes.StudyRandomScreen + "/" + backstackEntry.arguments?.getString(
                        "languageCode"
                    )
                )
            },
                onToAll = { navController.navigate(
                    route = Routes.StudyAllScreen + "/" + backstackEntry.arguments?.getString(
                        "languageCode"
                    )
                )},
                onBackClick = {navController.popBackStack()})

        }

        composable(
            route = Routes.StudyRandomScreen + "/{languageCode}",
            arguments = listOf(navArgument(name = "languageCode") {
                type = NavType.StringType
                defaultValue = "en"
            })
        ) { backstackEntry ->
            val viewModel: AndroidStudyRandomViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            LaunchedEffect(key1 = null) {
                viewModel.onEvent(
                    StudyRandomEvent.SelectLanguage(
                        UiLanguage.byCode(
                            backstackEntry.arguments?.getString("languageCode") ?: "en"
                        )
                    )
                )
            }
            StudyScreen(state = state, onEvent = { event ->
                when (event) {
                    is StudyRandomEvent.BackClick -> navController.popBackStack()
                    else                          -> viewModel.onEvent(event)
                }
            })
        }

        composable(route = Routes.StudyAllScreen + "/{languageCode}",
            arguments = listOf(navArgument(name = "languageCode") {
                type = NavType.StringType
                defaultValue = "en"
            })
        ) { backstackEntry ->

            val viewModel: AndroidStudyAllViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            LaunchedEffect(key1 = null) {
                viewModel.onEvent(
                    StudyAllEvent.SelectLanguage(
                        UiLanguage.byCode(
                            backstackEntry.arguments?.getString("languageCode") ?: "en"
                        )
                    )
                )
            }
            StudyAllScreen(state = state, onEvent = {event ->
                when(event){
                    StudyAllEvent.BackClick -> navController.popBackStack()
                    else -> viewModel.onEvent(event)
                }
            })






        }


    }
}