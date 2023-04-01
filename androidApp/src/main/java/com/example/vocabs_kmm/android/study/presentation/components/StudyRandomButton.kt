package com.example.vocabs_kmm.android.study.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.study.presentation.StudyEvent
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme

@Composable
fun StudyRandomButton(onEvent: (StudyEvent) -> Unit) {
    OutlinedButton(
        onClick = { onEvent(StudyEvent.ShowNextCard) },
        shape = CircleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground
        ),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.onBackground)
    ) {
        Text(
            text = stringResource(R.string.study_random_flash_card),
            style = MaterialTheme.typography.button
        )
    }
}

@Preview
@Composable
fun StudyRandomButtonPreview(){
    VocabsTheme {
        StudyRandomButton(onEvent = {})
    }
}
