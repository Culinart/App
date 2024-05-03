package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import org.w3c.dom.Text
import sptech.culinart.ui.theme.CulinartTheme

class PerfilSenhaEAutenticacao : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPerfilPagamento("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPerfilSenhaEAutenticacao(name: String, modifier: Modifier = Modifier) {
    Column {
        val alterarSenha = remember { mutableStateOf(false) }
        val senha = remember { mutableStateOf("") }
        val novaSenha = remember { mutableStateOf("") }
        val confirmarSenha = remember { mutableStateOf("") }
        ComponenteHader(name = "Android", modifier = Modifier.zIndex(99f))

        Spacer(modifier = Modifier.height(45.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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
                    text = "Senha e Autenticação",
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

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = "Alterar Senha",
                    style = TextStyle(
                        Color(4, 93, 83),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W900
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                if(!alterarSenha.value){
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = { /*TODO*/
                            alterarSenha.value = true
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
                            text = "Alterar",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                    Column(modifier = Modifier
                        .fillMaxWidth()){
                        Text(
                            text = "Apagar Conta",
                            style = TextStyle(
                                Color(4, 93, 83),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W900
                            ),
                            modifier = Modifier.align(Alignment.Start)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Apagar sua conta é uma atitude irreversível." +
                                    " Ao tomar essa decisão, esteja ciente de que todos os dados associados à sua " +
                                    "conta serão permanentemente removidos e não poderão ser recuperados.",
                            style = TextStyle(
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .align(Alignment.Start)
                                .fillMaxWidth(.9f)
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(197, 197, 197),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Apagar Conta",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )
                        }
                    }
                }else{
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .size(width = 320.dp, height = 270.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp, start = 10.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                TextField(
                                    value = senha.value,
                                    onValueChange = { senha.value = it },
                                    label = {
                                        Text(
                                            "Senha",
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier.padding(bottom = 15.dp)
                                        )
                                    },
                                    placeholder = { Text("*********") },
                                    colors = TextFieldDefaults.colors(
                                        unfocusedLabelColor = Color(4, 93, 83),
                                        focusedLabelColor = Color(4, 93, 83),
                                        unfocusedContainerColor = Color(249, 251, 251),
                                        focusedContainerColor = Color(232, 240, 239),
                                        unfocusedTextColor = Color(107, 107, 107, 255),
                                        focusedTextColor = Color.Black
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Password
                                    )
                                )

                                Spacer(modifier = Modifier.height(25.dp))

                                TextField(
                                    value = novaSenha.value,
                                    onValueChange = { novaSenha.value = it },
                                    label = {
                                        Text(
                                            "Nova Senha",
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier.padding(bottom = 15.dp)
                                        )
                                    },
                                    placeholder = { Text("*********") },
                                    colors = TextFieldDefaults.colors(
                                        unfocusedLabelColor = Color(4, 93, 83),
                                        focusedLabelColor = Color(4, 93, 83),
                                        unfocusedContainerColor = Color(249, 251, 251),
                                        focusedContainerColor = Color(232, 240, 239),
                                        unfocusedTextColor = Color(107, 107, 107, 255),
                                        focusedTextColor = Color.Black
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Password
                                    )
                                )

                                Spacer(modifier = Modifier.height(25.dp))

                                TextField(
                                    value = confirmarSenha.value,
                                    onValueChange = { confirmarSenha.value = it },
                                    label = {
                                        Text(
                                            "Confirmar Senha",
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.Bold
                                            ),
                                            modifier = Modifier.padding(bottom = 15.dp)
                                        )
                                    },
                                    placeholder = { Text("*********") },
                                    colors = TextFieldDefaults.colors(
                                        unfocusedLabelColor = Color(4, 93, 83),
                                        focusedLabelColor = Color(4, 93, 83),
                                        unfocusedContainerColor = Color(249, 251, 251),
                                        focusedContainerColor = Color(232, 240, 239),
                                        unfocusedTextColor = Color(107, 107, 107, 255),
                                        focusedTextColor = Color.Black
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Password
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(45.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { /*TODO*/
                                    alterarSenha.value = false},
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilSenhaEAutenticacaoPreview() {
    CulinartTheme {
        TelaPerfilSenhaEAutenticacao("Android")
    }
}