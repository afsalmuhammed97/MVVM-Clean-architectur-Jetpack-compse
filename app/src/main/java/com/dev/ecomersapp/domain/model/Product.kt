package com.dev.ecomersapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class Product(
val id: Int,
val title: String,
val price: Double,
val description: String,
val category: String,
val image: String
) : Parcelable
