package com.example.composeapp.core.network

import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import okhttp3.OkHttpClient
import org.junit.Before

abstract class NetworkModuleTestSetup {
    @Before
    open fun setup() {
        clearAllMocks()

        MockKAnnotations.init(this)
    }

    protected fun provideCommonOkHttpClient(): OkHttpClient {
        return NetworkModule.provideCommonOkHttpClient(
            builder = NetworkModule.provideOkHttpClientBuilder(),
            loggingInterceptor = NetworkModule.provideLoggingInterceptor(),
        )
    }

    protected fun provideMoshiMock(): Moshi {
        return NetworkModule.provideMoshi()
    }
}
