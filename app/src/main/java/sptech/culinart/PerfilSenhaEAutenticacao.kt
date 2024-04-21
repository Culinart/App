package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
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
                    TelaPerfilSenhaEAutenticacao("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPerfilSenhaEAutenticacao(name: String, modifier: Modifier = Modifier) {

    var editarDadosPessoais = mutableStateOf(false)
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

            Spacer(modifier = Modifier.height(55.dp))

            if (!editarDadosPessoais.value) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Box(
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .shadow(elevation = 3.dp, shape = RoundedCornerShape(12.dp))
                            .padding(8.dp)
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
                        onClick = { /*TODO*/ },
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