package com.dev.ecomersapp.presentation.ui.features.navigation

sealed class Screen(val rout: String) {

    object Products: Screen("products")

    object ProductDetail: Screen("product_detail")
}