package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.LightGreen
import com.example.vocabs_kmm.android.core.presentation.theme.LightPurple
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme


@Composable
fun AnimatedImageLoadingIcon() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.25f),
        contentAlignment = Alignment.Center
    ) {

        val infiniteTransition = rememberInfiniteTransition(label = "infinite transition animation")
        // start and end color for icon
        val startColor = LightGreen
        val endColor = LightPurple

        val animatedColor by infiniteTransition.animateColor(
            initialValue = startColor,
            targetValue = endColor,
            animationSpec = infiniteRepeatable(
                tween(1000, easing = FastOutLinearInEasing),
                RepeatMode.Reverse,
            ),
            label = "animated color"
        )

        val pulsatingFactor by infiniteTransition.animateFloat(
            initialValue = 1f,
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
            label = "pulsating animation"
        )

        Icon(
            imageVector = Icons.Default.Brush,
            contentDescription = stringResource(
                R.string.brush
            ),
            tint = animatedColor,
            modifier = Modifier.size(45.dp * pulsatingFactor)
        )
    }
}

@Preview
@Composable
fun AnimatedImageLoadingIconPreview(){
    VocabsTheme {
        AnimatedImageLoadingIcon()
    }
}
