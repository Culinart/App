package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import sptech.culinart.ui.theme.CulinartTheme

class PerfilInformacoesPessoais : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPerfilInformacoesPessoais("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPerfilInformacoesPessoais(name: String, modifier: Modifier = Modifier) {

    val editarDadosPessoais = remember { mutableStateOf(false) }
    val novoNome = remember { mutableStateOf("") }
    val novoEmail = remember { mutableStateOf("") }
    ComponenteHader(name = "Android", modifier = Modifier.zIndex(99f))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Perfil",
                style = TextStyle(
                    Color(4, 93, 83),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Informações Pessoais",
                style = TextStyle(
                    Color(220, 119, 38),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(android.graphics.Color.parseColor("#C1CECD")))
            )

            Spacer(modifier = Modifier.height(55.dp))

            if (!editarDadosPessoais.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .shadow(elevation = 2.8.dp, shape = RoundedCornerShape(6.dp))
                            .padding(20.dp)
                            .size(width = 320.dp, height = 175.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Nome",
                                style = TextStyle(
                                    Color(4, 93, 83),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Nome Completo",
                                style = TextStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 18.sp
                                ),
                                modifier = Modifier.padding(start = 15.dp)
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            Text(
                                text = "Email",
                                style = TextStyle(
                                    Color(4, 93, 83),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "user@mail.com",
                                style = TextStyle(
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 18.sp
                                ),
                                modifier = Modifier.padding(start = 15.dp)
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                    Button(
                        onClick = { /*TODO*/
                            editarDadosPessoais.value = true
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(255, 159, 28),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Editar",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .size(width = 320.dp, height = 175.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, start = 10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            TextField(
                                value = novoNome.value,
                                onValueChange = { novoNome.value = it },
                                label = {
                                    Text(
                                        "Nome",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(bottom = 15.dp)
                                    )
                                },
                                placeholder = { Text("Nome Completo") },
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
                                )
                            )

                            Spacer(modifier = Modifier.height(25.dp))

                            TextField(
                                value = novoEmail.value,
                                onValueChange = { novoEmail.value = it },
                                label = {
                                    Text(
                                        "Email",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                    )
                                },
                                placeholder = { Text("user@email.com") },
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
                                )
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(70.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { /*TODO*/
                                      editarDadosPessoais.value = false},
                            modifier = Modifier
                                .width(125.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(197, 197, 197),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Cancelar",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .width(125.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(197, 197, 197),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Confirmar",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(150.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilInformacoesPessoaisPreview() {
    CulinartTheme {
        TelaPerfilInformacoesPessoais("Android")
    }
}