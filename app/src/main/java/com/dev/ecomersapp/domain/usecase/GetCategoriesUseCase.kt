package com.dev.ecomersapp.domain.usecase

import com.dev.ecomersapp.data.repository.ProductRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getCategories()
}