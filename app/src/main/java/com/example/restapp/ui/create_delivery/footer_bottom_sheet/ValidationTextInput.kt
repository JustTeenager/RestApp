package com.example.restapp.ui.create_delivery.footer_bottom_sheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun ValidationTextInput(
    modifier: Modifier,
    initialField: String,
    onValueChanged: (String) -> Unit = { },
    checkIfError: (String) -> Boolean = { false },
    @StringRes labelText: Int,
    @StringRes placeHolderText: Int,
    @StringRes errorText: Int
) {

    var field by remember {
        mutableStateOf(initialField)
    }

    Column(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = field,
            onValueChange = {
                field = it
                onValueChanged(it)
            },
            placeholder = { Text(text = stringResource(placeHolderText)) },
            label = { Text(text = stringResource(labelText)) },
            isError = checkIfError(field),
        )
        if (checkIfError(field)) {
            Text(
                text = stringResource(errorText),
                color = Color.Red,
                style = MaterialTheme.typography.body2
            )
        }
    }
}