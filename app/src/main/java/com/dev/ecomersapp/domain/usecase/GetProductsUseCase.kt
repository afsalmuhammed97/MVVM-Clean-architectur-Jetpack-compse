package com.dev.ecomersapp.domain.usecase

import com.dev.ecomersapp.data.NetworkResult
import com.dev.ecomersapp.data.repository.ProductRepository
import com.dev.ecomersapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(private val repository: ProductRepository) {




    suspend operator fun invoke(): Flow<NetworkResult<List<Product>>>{
        return repository.getAllProducts()
    }


}