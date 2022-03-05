package com.example.restapp.ui.create_delivery.footer_bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restapp.R
import com.example.restapp.data.model.Cart
import com.example.restapp.ui.theme.spacing

@Composable
fun Footer(
    modifier: Modifier,
    cart: Cart,
    productCount: Int,
    viewModel: FooterViewModel = hiltViewModel()
) {

    var cartString by remember {
        mutableStateOf(cart.address)
    }

    Column(
        modifier = modifier
    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = cartString,
            onValueChange = {
                cartString = it
                viewModel.obtainEvent(FooterViewModel.Event.OnAddressChanged(it))
            },
            label = { Text(text = stringResource(R.string.add_address_label)) },
            isError = cart.address.isEmpty(),
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = cart.totalPrice.toString()
            )
            Text(
                text = productCount.toString()
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { viewModel.obtainEvent(FooterViewModel.Event.OnBuyConfirmed(cart)) }
        ) {
            Text(text = stringResource(id = R.string.buy_btn_title))
        }
    }
}