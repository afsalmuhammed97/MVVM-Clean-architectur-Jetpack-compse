package com.dev.ecomersapp.presentation.ui.features.productdetail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.dev.ecomersapp.domain.model.Product
import com.dev.ecomersapp.presentation.ui.features.componants.ZoomableImage

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier, product: Product?,
    onBackClick: () -> Unit = {}
) {


    if (product == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(text = "No Product found")
        }
        return
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = product.title, maxLines = 1,modifier=modifier.padding(end = 10.dp))
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }

            })

    }) { padding ->

        Column(modifier = modifier
            .padding(padding)
            .verticalScroll(rememberScrollState())) {


            ZoomableImage(imageUrl = product.image)
//            Image(
//                painter = rememberAsyncImagePainter(product.image),
//                contentDescription = product.title,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(280.dp),
//                contentScale = ContentScale.Fit
//            )
   /*
            var scale by remember { mutableStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }

            BoxWithConstraints (modifier = modifier.fillMaxWidth().aspectRatio(1280f/950f)) {
                val state= rememberTransformableState { zoomChange, panChange, rotationChange ->
                    scale=(scale* zoomChange).coerceIn(1f,5f)

                    val extraWidth=(scale-1) *constraints.maxWidth
                    val extraHight=(scale-1) *constraints.maxHeight
                    val maxX=extraWidth/2
                    val maxY=extraHight/2

                    offset=Offset(
                        x = (offset.x + scale* panChange.x).coerceIn(-maxX,maxX),
                        y = (offset.y +scale* panChange.y).coerceIn(-maxY,maxY)
                    )
                }
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp).graphicsLayer{
                            scaleX=scale
                            scaleY=scale
                            translationX=offset.x
                            translationY=offset.y
                        }
                        .transformable(state),
                    contentScale = ContentScale.Fit
                )
            }

    */




            Spacer(modifier = Modifier.height(16.dp))

            Column(modifier = modifier.padding(16.dp)) {

                Text(text = product.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "$ ${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = product.category,
                    style = MaterialTheme.typography.labelMedium
                )


                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium
                )

            }


        }


    }

}