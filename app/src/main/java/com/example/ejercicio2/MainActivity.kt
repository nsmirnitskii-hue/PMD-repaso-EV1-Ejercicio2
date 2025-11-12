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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejercicio2Theme {
                Aplicacion()
            }
        }
    }
}

data class Vehiculos(var tipo: String, var marca: String, var precio: Int)
@Composable
fun Aplicacion(){
    var tipo by remember{mutableStateOf("")}
    var marca by remember{mutableStateOf("")}
    var precio by remember{mutableStateOf("")}
    var listaMoto = remember{mutableListOf<String>()}
    var listaCoche = remember{mutableListOf<String>()}
    var respuests: String = ""
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
            while(true)
                    try {
                       if (tipo == "moto" || tipo == "coche"){
                           if (tipo == "moto") {
                               listaMoto.add(tipo, marca,precio )
                               Toast.makeText(context, "los datos se han insertado a la lista de los motos", Toast.LENGTH_LONG)
                           }else if (tipo == "coche"){
                               listaCoche.add(tipo, marca,precio )
                               Toast.makeText(context, "los datos se han insertado a la lista de los coches", Toast.LENGTH_LONG)
                           }
                        }else{
                           Toast.makeText(context, "todos los campos tienen que estar rellenos", Toast.LENGTH_LONG).show()
                       }
                    }catch (e: Exception){
                        Toast.makeText(context, "El tipo de vehiculo no valido, puede ser solo o moto o coche", Toast.LENGTH_LONG).show()
                    }
        }) {Text("añadir") }
        Text(listaMoto.toString())
        Text(listaCoche.toString())

    }


}
