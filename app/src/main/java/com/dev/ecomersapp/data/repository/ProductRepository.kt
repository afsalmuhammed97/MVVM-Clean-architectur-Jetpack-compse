package com.dev.ecomersapp.data.repository

import com.dev.ecomersapp.data.NetworkResult
import com.dev.ecomersapp.data.local.dao.ProductDao
import com.dev.ecomersapp.data.remote.api.ProductApiService
import com.dev.ecomersapp.data.toDomain
import com.dev.ecomersapp.data.toEntity
import com.dev.ecomersapp.domain.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ProductApiService,
    private val productDao: ProductDao

) {


//    suspend fun getAllProducts1(): Flow<NetworkResult<List<Product>>> = flow {
//        emit(NetworkResult.Loading())
//
//
//        val response = apiService.getAllProducts()
//        if (response.isSuccessful) {
//
//
//            emit(NetworkResult.Success(response.body()))
//        } else {
//
//
//            emit(NetworkResult.Error(response.errorBody()?.string()))
//        }
//
//    }.catch { e ->
//        emit(NetworkResult.Error(e.localizedMessage))
//    }


    fun getAllProducts(): Flow<NetworkResult<List<Product>>> = flow {

        emit(NetworkResult.Loading())

        // 1️⃣ Get cached data first
        val cachedProducts = productDao.getProducts().first()

        if (cachedProducts.isNotEmpty()) {
            emit(NetworkResult.Success(cachedProducts.toDomain()))
        }

        try {
            // 2️⃣ Fetch from network
            val response = apiService.getAllProducts()

            if (response.isSuccessful && response.body() != null) {

                val dtos = response.body()!!

                // 3️⃣ Save to DB
                productDao.insertProducts(dtos.toEntity())

                // 4️⃣ Emit updated data from DB
                emit(
                    NetworkResult.Success(
                        productDao.getProducts().first().toDomain()
                    )
                )

            } else {
                // If API failed and no cache
                if (cachedProducts.isEmpty()) {
                    emit(NetworkResult.Error(response.errorBody()?.string()))
                }
            }

        } catch (e: Exception) {

            // If no internet & no cache
            if (cachedProducts.isEmpty()) {
                emit(NetworkResult.Error(e.localizedMessage))
            }
        }

    }.catch { e ->
        emit(NetworkResult.Error(e.localizedMessage))
    }


    suspend fun getCategories(): Flow<NetworkResult<List<String>>> = flow {
        emit(NetworkResult.Loading())

        val response = apiService.getCategories()


        if (response.isSuccessful) {
            emit(NetworkResult.Success(response.body()))
        } else {
            emit(NetworkResult.Error(response.errorBody()?.string()))
        }
    }.catch { e ->
        emit(NetworkResult.Error(e.localizedMessage))
    }

}