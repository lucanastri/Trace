package com.kizune.trace.ui

import androidx.annotation.StringRes
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TraceButton(
    onClick: () -> Unit,
    @StringRes text: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults
            .buttonColors(),
        elevation = ButtonDefaults.elevation(
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            focusedElevation = 0.dp,
            defaultElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onPrimary
        )
    }
}