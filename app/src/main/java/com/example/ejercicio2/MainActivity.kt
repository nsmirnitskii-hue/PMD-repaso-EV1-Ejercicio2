package com.example.ejercicio2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ejercicio2.ui.theme.Ejercicio2Theme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio2Theme {
                Ruta()
            }
        }
    }
}

@Composable
fun Ruta(){
    var listaMoto = remember{mutableStateListOf<Vehiculos>()}
    var listaCoche = remember{mutableStateListOf<Vehiculos>()}
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "App")
    {
        composable("App") { Aplicacion(navController,listaMoto, listaCoche) }
        composable("Vista") {VistaListas(navController, listaMoto, listaCoche)  }
    }
}

data class Vehiculos(var tipo: String, var marca: String, var precio: Int)
@Composable
fun Aplicacion(navController: NavController,listaMoto: MutableList<Vehiculos>, listaCoche: MutableList<Vehiculos>){
    var tipo by remember{mutableStateOf("")}
    var marca by remember{mutableStateOf("")}
    var precio by remember{mutableStateOf("")}
    var ultimoVehiculo by remember{mutableStateOf("")}
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
        .padding(8.dp),
        Arrangement.Top,
        Alignment.CenterHorizontally) {

        Text("Introduce el tipo de veiculo")
        OutlinedTextField(
            value = tipo,
            onValueChange = {newTipo ->
                tipo = newTipo
            },
            label = {Text("tipo")}
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Introduce el marca de veiculo")
        OutlinedTextField(
            value = marca,
            onValueChange = {newMarca ->
                marca = newMarca
            },
            label = {Text("marca") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Introduce el precio de veiculo")
        OutlinedTextField(
            value = precio,
            onValueChange = {newPrecio ->
                precio = newPrecio
            },
            label = {Text("precio")}
        )
        Button({
            tipo.replace(" ","")
            // validar todos los textfield con info
            if(tipo.isBlank() || marca.isBlank() || precio.isBlank()) {
                Toast.makeText(context, "Faltan datos por informar", Toast.LENGTH_LONG).show()
            }
            // validar tipo de vehiculos
            // aqui mensaje de que solo se aceptan coches y motos
            else if( tipo != "moto" && tipo != "coche") {
                Toast.makeText(context, "vehiculo mal", Toast.LENGTH_LONG).show()
            } else {
                if (tipo == "moto") {
                    listaMoto.add(Vehiculos(tipo, marca,precio.toInt() ))
                    Toast.makeText(context, "los datos se han insertado a la lista de los motos", Toast.LENGTH_LONG).show()
                }
                if (tipo == "coche"){
                    listaCoche.add(Vehiculos(tipo, marca,precio.toInt() ))
                    Toast.makeText(context, "los datos se han insertado a la lista de los coches", Toast.LENGTH_LONG).show()
                }
                ultimoVehiculo = "tipo:$tipo, marca:$marca,precio:$precio"
                tipo = ""
                marca = ""
                precio = ""
            }
        }) {Text("añadir") }
        Button({
            navController.navigate("Vista")
            //listaMoto.clear()
            //listaCoche.clear()
        }) {Text("ver listas") }
        Text("El ultimo vehiculo que se ha introducido es $ultimoVehiculo")


    }


}

