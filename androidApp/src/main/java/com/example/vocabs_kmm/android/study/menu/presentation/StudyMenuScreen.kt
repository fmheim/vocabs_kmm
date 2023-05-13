package com.example.vocabs_kmm.android.study.menu.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.study.menu.domain.StudyTypes

@Composable
fun StudyMenuScreen(onToRandom: () -> Unit, onToAll: () -> Unit, onBackClick: () -> Unit) {


    Scaffold(
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth(0.5f)
                    .padding(all = 16.dp)
                    .clickable { onBackClick()}) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier.size(30.dp)
                )
            }
        },
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StudyTypes.values().forEach {
                Button(onClick = {
                    when (it) {
                        StudyTypes.RANDOM -> onToRandom()
                        StudyTypes.ALL    -> onToAll()
                    }
                }) {
                    Text(text = it.name)
                }

            }
        }
    }
}



