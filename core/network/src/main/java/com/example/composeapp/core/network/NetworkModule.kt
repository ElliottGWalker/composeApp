package com.example.composeapp.core.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class NetworkMoshi

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class NoAuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthRetrofit

@Module
@InstallIn(Singleton::class)
interface NetworkModule {
    companion object {
        @Provides
        @Singleton
        @NetworkMoshi
        fun provideMoshi(): Moshi =
            Moshi
                .Builder()
                .build()

        private fun buildRetrofit(
            @NetworkMoshi moshi: Moshi,
            okHttpClient: OkHttpClient,
            baseUrl: String,
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @Provides
        @Singleton
        @NoAuthOkHttpClient
        fun provideNoAuthOkHttpClient(
            builder: OkHttpClient.Builder,
            loggingInterceptor: HttpLoggingInterceptor,
        ): OkHttpClient {
            builder.interceptors().clear()
            return builder
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Provides
        @Singleton
        @NoAuthRetrofit
        fun provideNoAuthRetrofit(
            @NetworkMoshi moshi: Moshi,
            @NoAuthOkHttpClient okHttpClient: OkHttpClient,
        ): Retrofit =
            buildRetrofit(
                moshi,
                okHttpClient,
                getBaseUrl(),
            )

        // This method could be changed to cater for different BaseUrls depending on Dev, Preprod, Prod environment
        private fun getBaseUrl(): String {
            return "https://cdn.develop.gymshark.com/"
        }
    }
}
