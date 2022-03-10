package com.example.restapp.ui.create_delivery.footer_bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.restapp.R

@Composable
fun EmailTextInput(
    modifier: Modifier,
    initialAddress: String,
    onValueChanged: (String) -> Unit
) {

    var address by remember {
        mutableStateOf(initialAddress)
    }

    Column(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = address,
            onValueChange = {
                address = it
                onValueChanged(it)
            },
            placeholder = { Text(text = stringResource(R.string.add_address_label)) },
            label = { Text(text = stringResource(R.string.add_address_label)) },
            isError = address.isEmpty(),
        )
        if (address.isEmpty()) {
            Text(
                text = "Ошибочка вышла",
                color = Color.Red,
                style = MaterialTheme.typography.body2
            )
        }
    }
}