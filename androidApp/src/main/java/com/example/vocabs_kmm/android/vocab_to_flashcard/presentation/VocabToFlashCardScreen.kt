package com.example.vocabs_kmm.android.vocab_to_flashcard.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme

@Composable
fun VocabToFlashcardScreen() {
    Scaffold(backgroundColor = MaterialTheme.colors.background) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = com.example.vocabs_kmm.android.R.drawable.img),
                contentDescription = "placeholder_image",
                modifier = Modifier.fillMaxHeight(0.25f),
                contentScale = ContentScale.FillWidth
            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.389f)) {
                Surface(
                    shape = RectangleWithCurvedBottomEdgeShape(),
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.85f)

                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Example sentence of an interesting topic, quite long but not too long.",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface
                        )
                    }


                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.primary, shape = CircleShape)
                            .padding(7.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Autorenew,
                            contentDescription = "re-generate",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .background(color = MaterialTheme.colors.primary, shape = CircleShape)
                            .padding(7.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "save",
                            modifier = Modifier.size(45.dp)
                        )
                    }
                }

            }


        }
    }


}

@Preview(showBackground = true)
@Composable
fun VocabToFlashcardScreenPreview() {
    VocabsTheme(darkTheme = true) {
        VocabToFlashcardScreen()
    }
}


class RectangleWithCurvedBottomEdgeShape() : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(
            path = drawPath(size)
        )
    }
}

fun drawPath(size: Size): Path {
    return Path().apply {
        reset()
        lineTo(size.width, 0f)
        lineTo(size.width, size.height)
        quadraticBezierTo(x1 = size.width / 2f, y1 = size.height * 1.15f, x2 = 0f, y2 = size.height)
        close()
    }
}