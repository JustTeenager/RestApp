package com.example.restapp.ui.create_delivery.footer_bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restapp.R
import com.example.restapp.data.model.Cart
import com.example.restapp.ui.base.ValidationTextInput
import com.example.restapp.ui.theme.spacing

@Composable
fun Footer(
    modifier: Modifier,
    cart: Cart,
    productCount: Int,
    viewModel: FooterViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier
    ) {
        ValidationTextInput(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            initialField = cart.address,
            checkIfError = { it?.isEmpty() ?: false },
            placeHolderText = stringResource(R.string.add_address_label),
            labelText = stringResource(R.string.add_address_label),
            errorText = stringResource(R.string.addr_error_text),
            onValueChanged = { viewModel.obtainEvent(FooterViewModel.Event.OnAddressChanged(it)) }
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.cart_price_title))
                    withStyle(style = SpanStyle(color = Color.Red)) {
                        append(
                            stringResource(
                                R.string.cart_price_convertion,
                                cart.totalPrice.toString()
                            )
                        )
                    }
                },
                style = MaterialTheme.typography.subtitle1,
                fontSize = 20.sp,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.cart_count_title, productCount.toString()),
                style = MaterialTheme.typography.subtitle1,
                fontSize = 20.sp,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.extraLarge)
                .align(Alignment.CenterHorizontally),
            onClick = {
                viewModel.obtainEvent(
                    FooterViewModel.Event.OnBuyConfirmed(cart, viewModel.getAddress())
                )
            }
        ) {
            Text(text = stringResource(id = R.string.buy_btn_title))
        }
    }
}