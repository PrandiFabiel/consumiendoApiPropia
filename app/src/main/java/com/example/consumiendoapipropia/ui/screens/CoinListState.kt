package com.example.consumiendoapipropia.ui.screens

import com.example.consumiendoapipropia.data.remote.dto.CoinsDto

data class CoinListState(
    val isLoading: Boolean = false,
    val exchanges: List<CoinsDto> = emptyList(),
    val error: String = ""
)
