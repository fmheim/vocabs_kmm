package com.example.vocabs_kmm.android.navigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.AndroidVocabToFlashCardViewModel
import com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.VocabToFlashcardScreen

@Composable
fun VocabsAppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.VocabToFlashcardScreen,
){

    NavHost(navController = navController, startDestination = startDestination){

        composable(route = Routes.VocabToFlashcardScreen){
            val viewModel: AndroidVocabToFlashCardViewModel = hiltViewModel()
            val state by viewModel.state.collectAsState()
            VocabToFlashcardScreen()

        }


    }

}