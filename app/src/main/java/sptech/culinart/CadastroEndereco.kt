package sptech.culinart

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sptech.culinart.ui.theme.CulinartTheme

class CadastroEndereco : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaCadastro("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaCadastroEndereco(name: String, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current

    val cep = remember {
        mutableStateOf("")
    }

    val estado = remember {
        mutableStateOf("")
    }

    val cidade = remember {
        mutableStateOf("")
    }

    val bairro = remember {
        mutableStateOf("")
    }

    val logradouro = remember {
        mutableStateOf("")
    }

    val numero = remember {
        mutableStateOf("")
    }

    val complemento = remember {
        mutableStateOf("")
    }

    val estados = listOf(
        "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA",
        "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN",
        "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    )
    val selectedEstado = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(249, 251, 251)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.mipmap.cadastroprimeiraetapa),
            contentDescription = "Cadastro etapa de Endereço",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            "Onde fica sua cozinha, Chef?",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                Color(255, 159, 28),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.width(30.dp))
                TextField(
                    value = cep.value,
                    onValueChange = { cep.value = it },
                    label = {
                        Text("CEP")
                    },
                    placeholder = { Text("00000-000") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(30.dp))

                Column {
                    Column(
                        modifier = Modifier
                            .width(150.dp)
                    ) {

                        Spacer(modifier = Modifier.height(8.dp))

                        Box() {
                            TextButton(onClick = { expanded.value = !expanded.value }) {

                                Text(
                                    text = if (selectedEstado.value.isNotEmpty()) selectedEstado.value else "Estado",
                                    color = if (selectedEstado.value.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }

                        }
                        DropdownMenu(
                            expanded = expanded.value,
                            onDismissRequest = { expanded.value = false },
                            modifier = Modifier
                                .background(Color.White)
                                .border(1.dp, Color(160, 160, 160))
                                .height(300.dp)
                        ) {
                            estados.forEach { estado ->
                                DropdownMenuItem(
                                    text = { Text(estado, color = Color.Black) },
                                    onClick = {
                                        selectedEstado.value = estado
                                        expanded.value = false
                                    }
                                )
                            }
                        }
                        Box(
                            modifier = Modifier
                                .width(180.dp)
                                .height(1.dp)
                                .background(color = Color.Black)
                        )
                    }
                }

                /*TextField(
                    value = estado.value,
                    onValueChange = { estado.value = it },
                    label = {
                        Text("Estado")
                    },
                    placeholder = { Text("São Paulo") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.weight(1f)
                )*/

                Spacer(modifier = Modifier.width(30.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.width(30.dp))
                TextField(
                    value = cidade.value,
                    onValueChange = { cidade.value = it },
                    label = {
                        Text("Cidade")
                    },
                    placeholder = { Text("São Paulo") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(30.dp))

                TextField(
                    value = bairro.value,
                    onValueChange = { bairro.value = it },
                    label = {
                        Text("Bairro")
                    },
                    placeholder = { Text("Morumbi") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(30.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.width(30.dp))
                TextField(
                    value = logradouro.value,
                    onValueChange = { logradouro.value = it },
                    label = {
                        Text("Logradouro")
                    },
                    placeholder = { Text("Rua Haddock Lobo") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(30.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                Spacer(modifier = Modifier.width(30.dp))
                TextField(
                    value = numero.value,
                    onValueChange = { numero.value = it },
                    label = {
                        Text("Número")
                    },
                    placeholder = { Text("000") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(30.dp))

                TextField(
                    value = complemento.value,
                    onValueChange = { complemento.value = it },
                    label = {
                        Text("Complemento")
                    },
                    placeholder = { Text("Apto 1") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(30.dp))
            }



            Spacer(modifier = Modifier.height(40.dp))


            Button(
                onClick =
                {val cadastroPlano = Intent(contexto, CadastroPlano::class.java)

                    contexto.startActivity(cadastroPlano)
                },
                modifier = Modifier.width(250.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(255, 159, 28),
                    contentColor = Color.White
                )
            ) {
                Text("Confirmar")
            }

    }

}

@Preview(showBackground = true)
@Composable
fun TelaCadastroEnderecoPreview() {
    CulinartTheme {
        TelaCadastroEndereco("Android")
    }
}