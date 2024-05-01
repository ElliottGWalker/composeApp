package com.example.composeapp.ui.components.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun CurrencyText(
    price: Double,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
) {
    val currencyFormat = NumberFormat.getCurrencyInstance()
    currencyFormat.maximumFractionDigits = 2
    currencyFormat.currency = Currency.getInstance(Locale.getDefault())
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
