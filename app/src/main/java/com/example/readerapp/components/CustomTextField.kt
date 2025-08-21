package com.example.readerapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    email: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    suffix: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = email,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        singleLine = true,
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholder)
        },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        suffix = suffix,
        shape = RoundedCornerShape(10.dp)
    )
}