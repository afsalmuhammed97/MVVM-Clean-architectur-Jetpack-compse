package com.dev.ecomersapp.presentation.ui.features.productsListing

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.ecomersapp.data.NetworkResult
import com.dev.ecomersapp.domain.model.Product
import com.dev.ecomersapp.presentation.ui.features.componants.CategoryFilter
import com.dev.ecomersapp.presentation.ui.features.componants.ProductCard
import kotlin.math.log
import androidx.compose.material.pullrefresh.*



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ProductScreen( viewModel: ProductsViewModel = hiltViewModel(),modifier: Modifier = Modifier, onProductClick: (Product) -> Unit
) {

    val  state by viewModel.productState.collectAsState()
    val categoryState by viewModel.categoriesState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

     val pullRefreshState=rememberPullRefreshState(
         refreshing = isRefreshing,
         onRefresh = {viewModel.refreshProducts()}
     )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Products")
                }
            )
        }
    ) { padding ->
//        Box(
//            modifier = Modifier
//             //   .padding(padding)
//                .fillMaxSize()
//                .pullRefresh(pullRefreshState)
//        ) //{

            Column(modifier = Modifier.padding(padding)) {

                if (categoryState is NetworkResult.Success) {
                    CategoryFilter(
                        categories = (categoryState as NetworkResult.Success).data ?: emptyList(),
                        onCategorySelected = viewModel::filterByCategory
                    )
                }


                when (state) {
                    is NetworkResult.Loading<*> -> {

                        Box(
                            modifier = Modifier.fillMaxSize().fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is NetworkResult.Error<*> -> {
                        val message = (state as NetworkResult.Error).message

                        Column(
                            modifier = modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = message ?: "Something went wrong")
                            Spacer(modifier.height(8.dp))
                            Button(onClick = { viewModel.fetchProducts(false) }) {
                                Text("Retry")
                            }

                        }
                    }

                    is NetworkResult.Success -> {

                        val products = state.data ?: emptyList()
                        LazyVerticalGrid(
                            modifier = modifier.fillMaxSize(), columns = GridCells.Fixed(2),
                            contentPadding = PaddingValues(8.dp)
                        )
                        {

                            items(products) { product ->

                                ProductCard(product) {
                                    onProductClick(product)
                                }
                            }
                        }
                    }


                }


            }
       // }
}
}