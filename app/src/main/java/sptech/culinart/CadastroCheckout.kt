package sptech.culinart

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.assinatura.PagamentoDTO
import sptech.culinart.ui.theme.CulinartTheme

class CadastroCheckout : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    TelaCadastroCheckout(extras)
                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun TelaCadastroCheckout(extras: Bundle?, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current
    var quantidadeRefeicao = remember { mutableStateOf("+ X Refeições") }
    var precoRefeicao = remember { mutableStateOf("R$00,00") }

    println(extras.toString())
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
                    "Checkout", style = TextStyle(
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
                    val qtdRefeicoes = extras?.getInt("qtdRefeicoes") ?: 0
                    val valorRefeicao = extras?.getDouble("valorRefeicao") ?: 0.0

                    Text("$qtdRefeicoes - Refeições")
                    Text("R$$valorRefeicao")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    drawRoundRect(
                        color = Color(140, 140, 140), cornerRadius = CornerRadius(0f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val valorPlano = extras?.getDouble("valorPlano") ?: 0.0

                    Text("Total")
                    Text("R$valorPlano")
                }

                Spacer(modifier = Modifier.height(30.dp))

            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        var exibirModal = false

        Button(
            onClick = {

                exibirModal = true
                val assinaturaApiService = RetrofitInstace.getAssinaturaApiService()
                val pagamentoApiService = RetrofitInstace.getPagamentoApiService()

                assinaturaApiService.criarAssinatura(3).enqueue(object : Callback<PagamentoDTO> {
                    override fun onResponse(
                        call: Call<PagamentoDTO>, response: Response<PagamentoDTO>
                    ) {
                        if (response.isSuccessful) {
                            val pagamentoDTO = response.body()
                            pagamentoDTO?.let { pagamento ->
                                val linkCobranca = pagamento.linkCobranca
                                if (!linkCobranca.isNullOrEmpty()) {

                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkCobranca))
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
                })

                var isPago = false

                fun verificarStatusPagamento() =
                    pagamentoApiService.atualizarStatusPagamento(3)
                        .enqueue(object : Callback<List<PagamentoDTO>> {
                            override fun onResponse(
                                call: Call<List<PagamentoDTO>>,
                                response: Response<List<PagamentoDTO>>
                            ) {
                                if (response.isSuccessful) {
                                    response.body()?.get(0)?.let { pagamentoDTO ->
                                        if (pagamentoDTO.statusTransacao == "approved" || pagamentoDTO.statusTransacao == "settled") {
                                            println("Pagamento efetuado com sucesso!")
                                            isPago = true
                                        } else {
                                            println("Pagamento não efetuado!")
                                        }
                                    }
                                }
                            }

                            override fun onFailure(call: Call<List<PagamentoDTO>>, t: Throwable) {
                                println(t)
                            }
                        })


                suspend fun verificarStatusPagamentoPeriodicamente() {
                    while (!isPago) {
                        verificarStatusPagamento()
                        delay(5000)
                    }

                }

                GlobalScope.launch {
                    verificarStatusPagamentoPeriodicamente()
                    if (isPago) {
                        val pedido = Intent(contexto, Pedido::class.java)
                        contexto.startActivity(pedido)
                    }
                }

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
        TelaCadastroCheckout(null)
    }
}