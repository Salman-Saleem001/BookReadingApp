package com.example.readerapp.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.readerapp.R

@Composable
fun ReaderLogo() {
    Text(
        text = stringResource(R.string.app_title),
        style = MaterialTheme.typography.headlineLarge.copy(color = Color.Red.copy(alpha = 0.5f)),
    )
}
