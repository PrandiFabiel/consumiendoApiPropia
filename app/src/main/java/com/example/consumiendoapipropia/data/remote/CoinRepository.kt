package com.example.consumiendoapipropia.data.remote

import com.example.consumiendoapipropia.data.remote.dto.CoinsDto
import com.example.consumiendoapipropia.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val api: CoinsApi
) {
    fun getCoins(): Flow<Resource<List<CoinsDto>>> = flow {
        try {
            emit(Resource.Loading())

            val exchanges = api.getCoins()

            emit(Resource.Success(exchanges))

        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP general"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Error intentando conectarse a internet..."))
        }

    }
}