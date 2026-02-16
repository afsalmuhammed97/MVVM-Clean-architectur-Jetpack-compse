package com.dev.ecomersapp.presentation.ui.features.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dev.ecomersapp.domain.model.Product
import com.dev.ecomersapp.presentation.ui.features.productdetail.ProductDetailScreen
import com.dev.ecomersapp.presentation.ui.features.productsListing.ProductScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()


    NavHost(navController = navController,
        startDestination = Screen.Products.rout){


        composable(Screen.Products.rout){

            ProductScreen(){product ->


                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("product", product)

              Log.d("MMM","product $product",)
                navController.navigate(Screen.ProductDetail.rout)
            }
        }

        composable(Screen.ProductDetail.rout){


            val product =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Product>("product")

            Log.d("MMM","product 2 $product",)
             ProductDetailScreen(product=product, onBackClick = {
                 navController.popBackStack()
             })
        }
    }

}