package sptech.culinart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sptech.culinart.ui.theme.CulinartTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.usuario.UsuarioExibicaoDTO
import sptech.culinart.api.data.usuario.UsuarioLoginDTO
import sptech.culinart.api.data.usuario.UsuarioTokenDTO


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaLogin("Android")
                }
            }
        }
    }
}

@Composable
fun TelaLogin(name: String, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current

    val nome = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    val telefone = remember {
        mutableStateOf("")
    }
    val senha = remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(249, 251, 251))
    ) {

        Image(
            painter = painterResource(id = R.mipmap.ondalaranjaforte),
            contentDescription = "Onda Laranja Forte",
            modifier = Modifier
                .fillMaxWidth(fraction = 0.4F)
                .wrapContentHeight(align = Alignment.Bottom)
                .align(Alignment.BottomEnd)
        )

        Image(
            painter = painterResource(id = R.mipmap.ondalaranjamedio),
            contentDescription = "Onda Laranja Médio",
            modifier = Modifier
                .fillMaxWidth(fraction = 0.35F)
                .wrapContentHeight(align = Alignment.Bottom)
                .wrapContentWidth(align = Alignment.Start)
                .align(Alignment.BottomStart)
        )

        Image(
            painter = painterResource(id = R.mipmap.ondalaranjaclaro),
            contentDescription = "Onda Laranja Claro",
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
    }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Bem Vindo,",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    Color(255,159,28),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                )
            )
            Text(
                "Chef!",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    Color(255,159,28),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 35.sp
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    placeholder = { Text("example@email.com") },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color(4, 93, 83),
                        focusedLabelColor = Color(4, 93, 83),
                        unfocusedContainerColor = Color(249, 251, 251),
                        focusedContainerColor = Color(232, 240, 239),
                        unfocusedTextColor = Color(107, 107, 107, 255),
                        focusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                TextField(
                    value = senha.value,
                    onValueChange = { senha.value = it },
                    label = { Text("Senha") },
                    placeholder = { Text("********") },
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

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {

                        // Faça a chamada ao serviço da API
                        val usuarioApiService = RetrofitInstace.getUsuarioApiService()

                        val credenciais = UsuarioLoginDTO(email = email.value, senha = senha.value)
                        usuarioApiService.login(credenciais).enqueue(object : Callback<UsuarioTokenDTO> {
                            override fun onResponse(call: Call<UsuarioTokenDTO>, response: Response<UsuarioTokenDTO>) {
                                if (response.isSuccessful) {
                                    val usuarioTokenDTO = response.body()
                                    println(response.body())
                                    val prefsManager = PreferencesManager.getInstance(contexto)
                                    if (usuarioTokenDTO != null) {
                                        usuarioTokenDTO.token.let { prefsManager.saveToken(it) }
                                        usuarioTokenDTO.nome.let { prefsManager.saveName(it) }
                                        usuarioTokenDTO.permissao.let { prefsManager.savePermission(it) }
                                        usuarioTokenDTO.userID.let { prefsManager.saveUserId(it) }
                                        usuarioTokenDTO.email.let { prefsManager.saveEmail(it) }
                                        usuarioTokenDTO.isAtivo.let { prefsManager.saveIsAtivo(it) }
                                    }
                                    println("Token salvo: ${prefsManager.getToken()}")
                                    println("Nome salvo: ${prefsManager.getName()}")
                                    val telaPedido = Intent(contexto, Pedido::class.java)
                                    contexto.startActivity(telaPedido)

                                } else {
                                    // Trate os erros de login aqui
                                    println("Deu Erro $response")
                                }
                            }

                            override fun onFailure(call: Call<UsuarioTokenDTO>, t: Throwable) {
                                // Trate os erros de rede aqui
                                println(t)
                            }
                        })

                    },
                    modifier = Modifier.width(250.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 8.dp,
                        pressedElevation = 4.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(46, 196, 182),
                        contentColor = Color.White
                    )
                ) {
                    Text("Entrar")
                }

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(100.dp, 2.dp)
                    ) {
                        drawRoundRect(
                            color = Color.Black,
                            cornerRadius = CornerRadius(100f)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("ou",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = 25.sp,
                            color = Color.Black
                        ))

                    Spacer(modifier = Modifier.width(10.dp))

                    Canvas(
                        modifier = Modifier
                            .size(100.dp, 2.dp)
                    ) {
                        drawRoundRect(
                            color = Color.Black,
                            cornerRadius = CornerRadius(100f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick =
                {val cadastroEndereco = Intent(contexto, CadastroEndereco::class.java)
                    contexto.startActivity(cadastroEndereco)
                    val cadastro = Intent(contexto, Cadastro::class.java)

                    contexto.startActivity(cadastro)
                },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Text(
                        "Cadastre uma conta",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(
                            Color(46, 196, 182),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 15.sp
                        )
                    )
                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TelaLoginPreview() {
    CulinartTheme {
        TelaLogin("Android")
    }
}