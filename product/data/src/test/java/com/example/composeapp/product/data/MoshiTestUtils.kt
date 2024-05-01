package com.example.composeapp.product.data

import com.example.composeapp.core.network.NetworkModule_Companion_ProvideMoshiFactory
import com.squareup.moshi.Moshi

fun getMoshi(): Moshi = NetworkModule_Companion_ProvideMoshiFactory().get()
