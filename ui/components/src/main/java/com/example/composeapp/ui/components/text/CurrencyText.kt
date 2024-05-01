package com.example.composeapp.ui.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import java.text.NumberFormat
import java.util.Currency

@Composable
fun CurrencyText(
    price: Double,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
) {
    val currencyFormat = NumberFormat.getCurrencyInstance()
    currencyFormat.maximumFractionDigits = 2
    // This currency code would come from core:config module based on the selected store (uk/us/etc)
    currencyFormat.currency = Currency.getInstance("GBP")
    val priceText = currencyFormat.format(price)
    Text(
        text = priceText,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CurrencyTextPreview() {
    CurrencyText(
        price = 10.0,
    )
}
