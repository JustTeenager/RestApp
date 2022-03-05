package com.example.restapp.ui.create_delivery.footer_bottom_sheet

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.restapp.R
import com.example.restapp.data.model.Cart
import com.example.restapp.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FooterBottomSheet(
    modifier: Modifier,
    productCount: Int,
    cart: Cart?,
    state: BottomSheetScaffoldState,
    content: @Composable (Modifier) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = state,
        sheetContent = {
            cart?.let {
                Log.d("tut_footer_bottom_sheet", cart.toString())
                Footer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(MaterialTheme.spacing.large),
                    cart = cart,
                    productCount = productCount
                )
            }
        },
        sheetPeekHeight = 1.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            content(Modifier.weight(7f))

            if (!cart?.productList.isNullOrEmpty()) {
                Log.d("tut_footer_bottom_sheet", state.bottomSheetState.isExpanded.toString())
                Button(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .weight(1f)
                        .padding(
                            start = MaterialTheme.spacing.large,
                            end = MaterialTheme.spacing.large
                        ),
                    onClick = {
                        coroutineScope.launch {
                            with(state.bottomSheetState) {
                                expand()
                            }
                        }
                    }
                ) {
                    Text(text = stringResource(R.string.confirm_cart_go_to_confirm_btn_title))
                }
            }
        }
    }
}