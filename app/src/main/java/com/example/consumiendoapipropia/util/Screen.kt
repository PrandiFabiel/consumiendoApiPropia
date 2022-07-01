package com.example.consumiendoapipropia.util

sealed class Screen(val route: String){
    object nameRegistro: Screen("RegistroCoins")
    object nameList: Screen("ListCoins")
}
