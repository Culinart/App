package sptech.culinart

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sptech.culinart.ui.theme.CulinartTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
               Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val horarios = listOf(
        "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
        "18:00", "19:00", "20:00", "21:00", "22:00"
    )
    val selectedHorario = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }

    Column {

        Box(modifier = Modifier.padding(16.dp)) {
            TextButton(onClick = { expanded.value = !expanded.value }) {

                Text(
                    text = if (selectedHorario.value.isNotEmpty()) selectedHorario.value else "Horário",
                    color = if (selectedHorario.value.isNotEmpty()) Color.Black else Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .width(180.dp)
                    .padding(top = 46.dp)
                    .height(0.5.dp)
                    .background(color = Color.Black)
            )
        }

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier
                .background(Color.White)
                .border(1.dp, Color(160, 160, 160))
                .height(300.dp)
        ) {
            horarios.forEach { horario ->
                DropdownMenuItem(
                    text = { Text(horario, color = Color.Black) },
                    onClick = {
                        selectedHorario.value = horario
                        expanded.value = false
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CulinartTheme {
        Greeting("Android")
    }
}