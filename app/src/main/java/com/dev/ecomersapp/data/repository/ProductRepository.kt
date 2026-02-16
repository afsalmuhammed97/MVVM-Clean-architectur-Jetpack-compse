package com.dev.ecomersapp.data.repository

import com.dev.ecomersapp.data.NetworkResult
import com.dev.ecomersapp.data.remote.api.ProductApiService
import com.dev.ecomersapp.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepository @Inject constructor ( private val apiService: ProductApiService) {



    suspend fun getAllProducts(): Flow<NetworkResult<List<Product>>>
           =flow {
               emit(NetworkResult.Loading())

        val response= apiService.getAllProducts()
        if (response.isSuccessful){
            emit(NetworkResult.Success(response.body()))
    }else{
            emit(NetworkResult.Error(response.errorBody()?.string()))
        }

    }.catch { e->
        emit(NetworkResult.Error(e.localizedMessage))
    }




    suspend fun getCategories(): Flow<NetworkResult<List<String>>>
    = flow{
        emit(NetworkResult.Loading())

        val response=apiService.getCategories()


        if (response.isSuccessful){
            emit(NetworkResult.Success(response.body()))
        }else{
            emit(NetworkResult.Error(response.errorBody()?.string()))
        }
    }.catch { e->
        emit(NetworkResult.Error(e.localizedMessage))
    }

}