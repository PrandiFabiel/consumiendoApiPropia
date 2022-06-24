package com.example.consumiendoapipropia.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.consumiendoapipropia.data.remote.dto.CoinsDto

@Composable
fun CoinsListScreen(
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
        }

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
    onClick: (CoinsDto) -> Unit
) {
    val img = "https://cdn.icon-icons.com/icons2/3369/PNG/512/ethereum_coin_money_cryptocurrency_icon_210995.png"
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(coinsDto) }
            .padding(10.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)) {
            Column() {
                Image(
                    painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current)
                        .data(img)
                        .crossfade(true)
                        .size(90)
                        .build()),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape),
                    contentDescription = "algo",
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = "Description: ",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = coinsDto.descripcion,
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = "Valor: ",
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = coinsDto.valor.toString(),
                    style = MaterialTheme.typography.body1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

    }
}