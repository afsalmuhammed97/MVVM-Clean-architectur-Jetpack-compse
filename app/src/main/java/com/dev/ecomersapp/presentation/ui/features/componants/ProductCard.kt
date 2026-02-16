package com.dev.ecomersapp.presentation.ui.features.componants

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
//import coil3.compose.AsyncImage
import com.dev.ecomersapp.domain.model.Product

@Composable
fun ProductCard(product: Product,modifier: Modifier = Modifier,onClick:(Product)-> Unit) {

    Card(modifier = modifier.fillMaxWidth()
        .padding(8.dp)
        .clickable{onClick(product)},
            shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {

        Column (modifier = modifier.padding(10.dp)){

            AsyncImage(model = product.image,
                contentDescription = product.title,
                modifier = modifier.fillMaxWidth().height(140.dp)
                    //.background(color = Color.Blue)
            )
            Spacer(modifier = modifier.height(8.dp))

            Text(text = product.title, maxLines = 1,
                fontWeight = FontWeight.Medium , fontSize = 14.sp)
            Spacer(modifier = modifier.height(4.dp))

            Text(text = "$ ${product.price}",fontWeight = FontWeight.Bold,
                fontSize = 16.sp)

            Spacer(modifier = Modifier.height(2.dp))


            Text(
                text = product.category,
                fontSize = 12.sp
            )



        }

    }

}