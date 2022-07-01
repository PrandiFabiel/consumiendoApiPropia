package com.example.consumiendoapipropia.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CoinsRegisterScreen (
    viewModel: CoinViewModel = hiltViewModel(),
    onSave: () -> Unit
){
    var nameError by rememberSaveable { mutableStateOf(false) }
    var priceError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Register of Coins")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "ArrowBack",
                        modifier = Modifier.clickable {
                            onSave()
                        }
                    )
                }
            )
        }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .absolutePadding(16.dp, 16.dp, 16.dp, 16.dp)
        ) {

            OutlinedTextField(
                value = "",
                onValueChange = {
                    nameError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Name")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CurrencyExchange,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text
                ),
                isError = nameError
            )

            TextObligatorio(error = nameError)

            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {
                    priceError = false
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Price")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    keyboardType = KeyboardType.Decimal
                ),
                isError = priceError
            )

            TextObligatorio(error = priceError)

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                          onSave()
                },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
            ) {
                Text(text = "Save")
            }

        }
    }
}


@Composable
fun TextObligatorio (error: Boolean){

    val assistiveElementText = if(error) "Error: Obligatrio" else "*Obligatorio"
    val assitiveElementColor = if(error){
        MaterialTheme.colors.error
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    }

    Text(
        text = assistiveElementText,
        color = assitiveElementColor,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
    )
}