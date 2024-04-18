package sptech.culinart

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.assinatura.AssinaturaDTO
import sptech.culinart.api.data.assinatura.PagamentoDTO
import sptech.culinart.api.data.usuario.UsuarioTokenDTO
import sptech.culinart.ui.theme.CulinartTheme

class CadastroCheckout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaCadastroCheckout("Android")
                }
            }
        }
    }
}

@Composable
fun TelaCadastroCheckout(name: String, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current
    var quantidadeRefeicao = remember { mutableStateOf("+ X Refeições") }
    var precoRefeicao =  remember { mutableStateOf("R$00,00") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(249, 251, 251))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.cadastroterceiraetapa),
            contentDescription = "Cadastro etapa de checkout",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(30.dp))
        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            border = BorderStroke(2.dp, Color(228, 228, 228)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    "Checkout",
                    style = TextStyle(
                        Color(255, 159, 28),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("+ X Refeições")
                    Text("R$00,00")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    drawRoundRect(
                        color = Color(140, 140, 140),
                        cornerRadius = CornerRadius(0f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total")
                    Text("R$00,00")
                }

                Spacer(modifier = Modifier.height(30.dp))

            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                val assinaturaApiService = RetrofitInstace.getAssinaturaApiService()

                assinaturaApiService.criarAssinatura(2).enqueue(
                    object : Callback<PagamentoDTO> {
                        override fun onResponse(
                            call: Call<PagamentoDTO>,
                            response: Response<PagamentoDTO>
                        ) {
                            if (response.isSuccessful) {
                                val pagamentoDTO = response.body()
                                pagamentoDTO?.let { pagamento ->
                                    // Obtém o link de cobrança
                                    val linkCobranca = pagamento.linkCobranca

                                    // Verifica se o link de cobrança não está vazio
                                    if (!linkCobranca.isNullOrEmpty()) {
                                        // Cria uma intent para abrir o navegador com a URL
                                        val intent =
                                            Intent(Intent.ACTION_VIEW, Uri.parse(linkCobranca))
                                        // Obtém o contexto apropriado
                                        contexto.startActivity(intent)
                                    } else {
                                        println("Link de cobrança vazio!")
                                    }
                                }
                            } else {
                                println("Deu ruim!")
                            }
                        }

                        override fun onFailure(call: Call<PagamentoDTO>, t: Throwable) {
                            // Trate os erros de rede aqui
                            println(t)
                        }
                    }
                )

            },
            modifier = Modifier.width(250.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(255, 159, 28),
                contentColor = Color.White
            )
        ) {
            Text("Finalizar Assinatura")
        }

        Spacer(modifier = Modifier.height(40.dp))

    }
}


@Preview(showBackground = true)
@Composable
fun TelaCadastroCheckoutPreview() {
    CulinartTheme {
        TelaCadastroCheckout("Android")
    }
}