package com.example.vocabs_kmm.android.core.presentation.components

import android.os.Build
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import com.example.vocabs_kmm.android.R
import com.example.vocabs_kmm.android.core.presentation.theme.VocabsTheme

@Composable
fun AsyncImageBox(
    image: Any?,
    onSuccess: (AsyncImagePainter.State.Success) -> Unit,

    ) {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.25f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = image,
            contentDescription = stringResource(R.string.generated_image),
            modifier = Modifier
                .zIndex(3f)
                .fillMaxHeight(),
            contentScale = ContentScale.FillHeight,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.25f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            },
            onSuccess = onSuccess,
        )
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) Surface(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f),
            color = MaterialTheme.colors.background.copy(alpha = 0.8f)
        ) {}
        AsyncImage(
            model = image,
            contentDescription = stringResource(R.string.generated_image_background),
            modifier = Modifier
                .blur(radius = 75.dp)
                .zIndex(1f)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth,

            )

    }
}

@Preview
@Composable
fun AsyncImageBoxPreview(){
    VocabsTheme {
        AsyncImageBox(image = R.drawable.img, onSuccess ={} )
    }
}