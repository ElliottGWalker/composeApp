package com.example.composeapp.product.data

import com.example.composeapp.core.network.NoAuthRetrofit
import com.example.composeapp.product.data.product.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ProductModule {
    companion object {
        @Provides
        @Singleton
        fun provideProductService(
            @NoAuthRetrofit retrofit: Retrofit,
        ): ProductService = retrofit.create(ProductService::class.java)
    }
}
