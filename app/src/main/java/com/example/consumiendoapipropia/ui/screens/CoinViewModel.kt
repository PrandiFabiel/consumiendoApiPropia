package com.example.consumiendoapipropia.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumiendoapipropia.data.remote.CoinRepository
import com.example.consumiendoapipropia.data.remote.dto.CoinsDto
import com.example.consumiendoapipropia.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinViewModel @Inject constructor(
    private val coinRepository: CoinRepository
) : ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    var name by mutableStateOf("")
    var price by mutableStateOf("")

    init {
        coinRepository.getCoins().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun save() {
        viewModelScope.launch {
            coinRepository.save(
                CoinsDto(
                    descripcion = name,
                    valor = price.toDouble()
                )
            )
        }
    }
}