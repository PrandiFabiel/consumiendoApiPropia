package com.example.consumiendoapipropia.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.consumiendoapipropia.data.remote.dto.CoinsDto
import java.text.DecimalFormat

@Composable
fun CoinsListScreen(
    onNavigateToRegistro: () -> Unit,
    viewModel: CoinViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("List of Coins")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToRegistro()
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Nuevo")
            }
        },

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(state.coins) { coins ->
                    CoinsItem(coinsDto = coins, onClick = {})
                }
            }
            if (state.isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

        }
    }
}

@Composable
fun CoinsItem(
    coinsDto: CoinsDto,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 7.dp)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .clickable { onClick() }
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(coinsDto.imgUrl)
                        .crossfade(true)
                        .size(90)
                        .build(),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape),
                    contentDescription = "Image",
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = coinsDto.descripcion)
            }
            val decimalFormat = DecimalFormat("#,###.######")
            Text(
                text = "$ " + decimalFormat.format(coinsDto.valor),
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Normal,
                letterSpacing = 3.sp,
            )
        }

    }
}
