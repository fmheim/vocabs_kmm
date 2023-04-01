package com.example.vocabs_kmm.android.study.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.study.presentation.StudyEvent
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme

@Composable
fun NextCardIconButton(onEvent: (StudyEvent) -> Unit) {
    IconButton(
        onClick = { onEvent(StudyEvent.ShowNextCard) },
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.primary, shape = CircleShape
            )
            .border(
                shape = CircleShape,
                width = 1.dp,
                color = if (isSystemInDarkTheme()) Color.Transparent else MaterialTheme.colors.surface
            )
            .padding(7.dp),
    ) {
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = stringResource(R.string.next_card),
            modifier = Modifier.size(45.dp),
            tint = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun NextCardIconButtonPreview(){
    VocabsTheme {
        NextCardIconButton(onEvent = {})
    }
}