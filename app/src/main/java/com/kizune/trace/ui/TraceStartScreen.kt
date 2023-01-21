package com.kizune.trace.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kizune.trace.R
import com.kizune.trace.theme.TraceTheme

@Composable
fun TraceStartScreen(
    onLoginButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(R.drawable.welcome_background)
            .crossfade(true)
            .build(),
        placeholder = asyncImagePlaceholder(
            placeholder = R.drawable.welcome_background
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                start = 16.dp
            )
            .systemBarsPadding(),
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxWidth(0.4f)
        )
        Spacer(modifier = Modifier.weight(1f))
        TraceButton(
            onClick = onLoginButtonClicked,
            text = R.string.login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    TraceTheme {
        TraceStartScreen(
            onLoginButtonClicked = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun StartScreenMediumPreview() {
    TraceTheme {
        TraceStartScreen(
            onLoginButtonClicked = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun StartScreenxtendedPreview() {
    TraceTheme {
        TraceStartScreen(
            onLoginButtonClicked = {}
        )
    }
}