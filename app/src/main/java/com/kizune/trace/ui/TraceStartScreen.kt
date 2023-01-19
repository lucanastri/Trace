package com.kizune.trace.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kizune.trace.R

@Composable
fun TraceStartScreen(
    onLoginButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = R.drawable.welcome_background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
                start = 16.dp
            ),
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.welcome_message),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        TraceButton(
            onClick = onLoginButtonClicked,
            text = R.string.login,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )
    }
}