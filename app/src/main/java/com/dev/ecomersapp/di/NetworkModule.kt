package com.dev.ecomersapp.di

import com.dev.ecomersapp.data.remote.api.ProductApiService
import com.dev.ecomersapp.data.repository.ProductRepository
import com.dev.ecomersapp.domain.usecase.GetProductsUseCase
import com.dev.ecomersapp.uittile.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
     return   Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ProductApiService=retrofit.create(ProductApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(productApiService: ProductApiService ) =ProductRepository(productApiService)

    @Provides
    @Singleton
    fun provideUseCases(productRepository:ProductRepository)= GetProductsUseCase(productRepository)



}