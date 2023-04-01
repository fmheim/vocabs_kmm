package com.example.vocabs_kmm.android.core.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.vocabs_kmm.core.presentation.UiLanguage

@Composable
fun LanguageDropDown(
    isExpanded: Boolean,
    close: () -> Unit,
    onLanguageSelected: (UiLanguage) -> Unit,
) {
    DropdownMenu(modifier = Modifier.background(
        Brush.horizontalGradient(
            colors = listOf(
                MaterialTheme.colors.surface, MaterialTheme.colors.background
            )
        )
    ),
        expanded = isExpanded,
        onDismissRequest = close) {
        UiLanguage.allLanguages.forEach {
            DropdownMenuItem(onClick = {
                onLanguageSelected(it)
                close()
            }) {
                AsyncImage(
                    model = it.drawableRes,
                    contentDescription = it.language.langName,
                    modifier = Modifier.size(30.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = it.language.langName, color = MaterialTheme.colors.onBackground)
            }
        }
    }
}