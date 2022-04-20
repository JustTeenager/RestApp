package com.example.restapp.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun ValidationTextInput(
    modifier: Modifier,
    initialField: String? = null,
    onValueChanged: (String) -> Unit = { },
    checkIfError: (String?) -> Boolean = { false },
    labelText: String? = null,
    placeHolderText: String? = null,
    errorText: String,
    transformation: VisualTransformation = VisualTransformation.None
) {

    var field by remember {
        mutableStateOf(initialField)
    }

    Column(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field ?: "",
            onValueChange = {
                field = it
                onValueChanged(it)
            },
            placeholder = placeHolderText?.let { { Text(text = placeHolderText) } },
            label = labelText?.let { { Text(text = labelText) } },
            isError = checkIfError(field),
            visualTransformation = transformation,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent
            )
        )
        if (checkIfError(field)) {
            Text(
                text = errorText,
                color = Color.Red,
                style = MaterialTheme.typography.body2
            )
        }
    }
}