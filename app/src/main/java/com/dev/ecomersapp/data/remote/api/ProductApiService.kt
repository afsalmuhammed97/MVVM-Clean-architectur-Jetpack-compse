package com.dev.ecomersapp.data.remote.api

import com.dev.ecomersapp.domain.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {


    @GET("products")
    suspend fun getAllProducts():Response<List<Product>>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>
}