package com.example.ejercicio2

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun VistaListas(navController: NavController, listaMoto: MutableList<Vehiculos>, listaCoche: MutableList<Vehiculos>){

    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        item {
            Button({
                navController.navigate("App")
            }) { Text("Volver") }
            Button({
                listaMoto.clear()
                listaCoche.clear()
            }) {Text("vaciar la lista")}
            listaCoche.forEach { Text(it.toString()) }
            listaMoto.forEach { Text(it.toString()) }


        }
    }
}