package com.example.consumiendoapipropia.data.remote

import com.example.consumiendoapipropia.data.remote.dto.CoinsDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CoinsApi {
    @GET("/Coins")
    suspend fun getCoins(): List<CoinsDto>

    @POST("/Coins")
    suspend fun save(@Body coin: CoinsDto): Response<CoinsDto>
}