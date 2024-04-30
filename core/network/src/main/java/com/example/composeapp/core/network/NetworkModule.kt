package com.example.composeapp.core.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class NetworkMoshi

@Qualifier
@Retention(AnnotationRetention.BINARY)
private annotation class CommonOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoAuthRetrofit

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {
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
        fun provideOkHttpClientBuilder() =
            OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

        @Provides
        @Singleton
        @CommonOkHttpClient
        fun provideCommonOkHttpClient(
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
        fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

        @Provides
        @Singleton
        @NoAuthRetrofit
        fun provideNoAuthRetrofit(
            @NetworkMoshi moshi: Moshi,
            @CommonOkHttpClient okHttpClient: OkHttpClient,
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
