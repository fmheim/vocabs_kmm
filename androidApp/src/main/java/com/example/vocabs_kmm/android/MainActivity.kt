package com.example.vocabs_kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.vocabs_kmm.vocab_to_flashcard.data.remote.OpenAiHttpClientFactory
import com.example.vocabs_kmm.vocab_to_flashcard.data.vocab_to_phrase.KtorVocabToPhraseClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {

                    val openAiHttpClient = OpenAiHttpClientFactory().create()
                    val vocabToPhraseClient = KtorVocabToPhraseClient(openAiHttpClient)
                    var phrase by remember {
                        mutableStateOf("")
                    }
                    LaunchedEffect(key1 = null) {

                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = phrase, fontSize = 34.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}




