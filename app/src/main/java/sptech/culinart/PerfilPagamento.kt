package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import sptech.culinart.ui.theme.CulinartTheme

class PerfilPagamento : ComponentActivity() {
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
fun TelaPerfilPagamento(name: String, modifier: Modifier = Modifier) {
    Column(Modifier.background(Color.White)) {
        ComponenteHeader(name = "Android", modifier = Modifier.zIndex(99f))

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
                .background(Color.White),
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
                    text = "Pagamento",
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

                Text(
                    text = "Veja a sua última cobrança através do link de pagamento.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

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
                        text = "Link de Pagamento",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    )
                }

                Spacer(modifier = Modifier.height(70.dp))

                Text(
                    text = "Cancelar Assinatura",
                    style = TextStyle(
                        Color(4, 93, 83),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W900
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Ao cancelar a sua assinatura, você indica que deseja descontinuar o " +
                            "serviço recebido pela Culinart. Você sempre será bem-vindo novamente " +
                            "e pode renovar a assinatura se desejar.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

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

                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilPagamentoPreview() {
    CulinartTheme {
        TelaPerfilPagamento("Android")
    }
}