package com.dev.ecomersapp.presentation.ui.features.productsListing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.ecomersapp.data.NetworkResult
import com.dev.ecomersapp.domain.model.Product
import com.dev.ecomersapp.domain.usecase.GetCategoriesUseCase
import com.dev.ecomersapp.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
): ViewModel(){


    private val _productStat= MutableStateFlow<NetworkResult<List<Product>>>(NetworkResult.Loading())

    val productState: StateFlow<NetworkResult<List<Product>>> =_productStat


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()


    private var allProducts: List<Product> = emptyList()


    private val _categoriesState =
        MutableStateFlow<NetworkResult<List<String>>>(NetworkResult.Loading())
    val categoriesState = _categoriesState

     init {
         fetchProducts(false)
         fetchCategories()
     }

    fun refreshProducts() {
        viewModelScope.launch {
            _isRefreshing.value = true
            fetchProducts(refresh = true)
            _isRefreshing.value = false
        }
    }


    fun  fetchProducts(refresh: Boolean){
        viewModelScope.launch {
            getProductsUseCase().collect {result ->
                if (result is NetworkResult.Success){
                    allProducts=result.data?:emptyList()
                }
                _productStat.value=result

            }
        }
    }


    fun fetchCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect {
                _categoriesState.value = it
            }
        }
    }

    fun filterByCategory(category: String?) {
        if (category.isNullOrBlank()) {
            _productStat.value = NetworkResult.Success(allProducts)
            return
        }

        val filtered = allProducts.filter {
            it.category.equals(category, ignoreCase = true)
        }

        _productStat.value = NetworkResult.Success(filtered)
    }



}