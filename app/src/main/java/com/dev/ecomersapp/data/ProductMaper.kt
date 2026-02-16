package com.dev.ecomersapp.data

import com.dev.ecomersapp.data.local.entity.ProductEntity
import com.dev.ecomersapp.domain.model.Product

fun ProductEntity.toDomainProduct(): Product {
    return Product(
        id = productId,
        title = title,
        price = price,
        description = description,
        category = category,
        image = image




    )
}

fun List<ProductEntity>.toDomain(): List<Product>{
    return map { it.toDomainProduct()  }
}

fun Product.toProductEntity(): ProductEntity{
    return ProductEntity(
        productId = id, title = title,
        price = price, description = description,
        category = category, image = image
    )
}

fun List<Product>.toEntity(): List<ProductEntity> {
    return map { it.toProductEntity() }
}