package com.example.consumiendoapipropia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.consumiendoapipropia.ui.screens.CoinsListScreen
import com.example.consumiendoapipropia.ui.screens.CoinsRegisterScreen
import com.example.consumiendoapipropia.ui.theme.ConsumiendoApiPropiaTheme
import com.example.consumiendoapipropia.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConsumiendoApiPropiaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CoinApp()
                }
            }
        }

    }
}

@Composable
fun CoinApp(
) {
    ConsumiendoApiPropiaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navHostController = rememberNavController( )

            NavHost(navController = navHostController, startDestination = Screen.nameList.route){
                composable(route = Screen.nameList.route ){
                    CoinsListScreen(
                        onNavigateToRegistro =  {navHostController.navigate(Screen.nameRegistro.route)}
                    )
                }
                composable(route = Screen.nameRegistro.route ){
                    CoinsRegisterScreen(onSave = {navHostController.navigateUp()})
                }

            }
        }
    }
}
