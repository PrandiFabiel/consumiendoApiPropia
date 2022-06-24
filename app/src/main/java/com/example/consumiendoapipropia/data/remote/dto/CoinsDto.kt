package com.example.consumiendoapipropia.data.remote.dto

import retrofit2.http.Url

data class CoinsDto(
    val id: String = "",
    val descripcion: String = "",
    val valor: Double = 0.0,
    val ImgUrl: String = "A"
)
