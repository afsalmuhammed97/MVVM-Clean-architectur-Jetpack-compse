package com.dev.ecomersapp.presentation.ui.features.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun ZoomableImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val minScale = 1f
    val maxScale = 5f
    val doubleTapZoom = 2.5f

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1280f / 950f)
    ) {

        val transformState = rememberTransformableState { zoomChange, panChange, _ ->
            scale = (scale * zoomChange).coerceIn(minScale, maxScale)

            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale - 1) * constraints.maxHeight
            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            offset = if (scale > 1f) {
                Offset(
                    x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
                )
            } else {
                Offset.Zero
            }
        }

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { tapOffset ->

                            if (scale > 1f) {
                                //  Zoom out
                                scale = 1f
                                offset = Offset.Zero
                            } else {
                                //  Zoom in to tap position
                                scale = doubleTapZoom

                                val centerX = constraints.maxWidth / 2f
                                val centerY = constraints.maxHeight / 2f

                                val dx = (centerX - tapOffset.x) * (scale - 1)
                                val dy = (centerY - tapOffset.y) * (scale - 1)

                                offset = Offset(dx, dy)
                            }
                        }
                    )
                }
                .transformable(transformState)
        )
    }



//    var scale by remember { mutableStateOf(1f) }
//    var offset by remember { mutableStateOf(Offset.Zero) }
//
//    BoxWithConstraints (modifier = modifier.fillMaxWidth().aspectRatio(1280f/950f)) {
//        val state= rememberTransformableState { zoomChange, panChange, rotationChange ->
//            scale=(scale* zoomChange).coerceIn(1f,5f)
//
//            val extraWidth=(scale-1) *constraints.maxWidth
//            val extraHight=(scale-1) *constraints.maxHeight
//            val maxX=extraWidth/2
//            val maxY=extraHight/2
//
//            offset=Offset(
//                x = (offset.x + scale* panChange.x).coerceIn(-maxX,maxX),
//                y = (offset.y +scale* panChange.y).coerceIn(-maxY,maxY)
//            )
//        }
//        Image(
//            painter = rememberAsyncImagePainter(imageUrl),
//            contentDescription ="",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(280.dp).graphicsLayer{
//                    scaleX=scale
//                    scaleY=scale
//                    translationX=offset.x
//                    translationY=offset.y
//                }
//                .transformable(state),
//            contentScale = ContentScale.Fit
//        )
//    }
}
