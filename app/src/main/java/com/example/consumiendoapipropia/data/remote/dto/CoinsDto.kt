package com.example.consumiendoapipropia.data.remote.dto

data class CoinsDto(
    val monedaId: String = "",
    val descripcion: String = "",
    val valor: Double = 0.0,
    val imgUrl: String? = ""
)
