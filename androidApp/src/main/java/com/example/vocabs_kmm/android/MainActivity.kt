package com.example.vocabs_kmm.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.vocabs_kmm.BuildConfig
import com.example.vocabs_kmm.vocab_to_phrase.data.open_ai_model.KtorOpenAiModelClient
import com.example.vocabs_kmm.vocab_to_phrase.data.remote.OpenAiHttpClientFactory
import com.example.vocabs_kmm.vocab_to_phrase.data.vocab_to_phrase.KtorVocabToPhraseClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                        val openAiHttpClient = OpenAiHttpClientFactory().create()
                        val ktorOpenAiModelClient = KtorOpenAiModelClient(openAiHttpClient)
                        LaunchedEffect(key1 = null){
                           val models = ktorOpenAiModelClient.getModels()
                            println(models)
                        }
                }
            }
        }
    }
}




