package com.example.consumiendoapipropia.data.remote

import com.example.consumiendoapipropia.data.remote.dto.CoinsDto
import retrofit2.http.GET

interface CoinsApi {
    @GET("/Coins")
    suspend fun getCoins(): List<CoinsDto>
}