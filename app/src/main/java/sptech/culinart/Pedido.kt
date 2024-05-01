package sptech.culinart

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.i18n.DateTimeFormatter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.pedido.DataEntregaDto
import sptech.culinart.api.data.pedido.DatasPedidosDto
import sptech.culinart.api.data.pedido.PedidoByDataDto
import sptech.culinart.api.data.pedido.PedidoDto
import sptech.culinart.api.utils.converterDataParaFormatoDescritivo
import sptech.culinart.ui.theme.CulinartTheme
import java.time.LocalDate

class Pedido : ComponentActivity() {
    private var screenDataDto: PedidoByDataDto? = null
    private val pedidosApiService = RetrofitInstace.getPedidosApiService()
    override fun onCreate(savedInstanceState: Bundle?) {

        getDatasPedidos()
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android", screenDataDto)
                }
            }

        }
    }

    private fun getDatasPedidos() {
        val prefsManager = PreferencesManager.getInstance(this)
        val userId = prefsManager.getUserId()
        val token = prefsManager.getToken()

            pedidosApiService.getDatasPedidos(userId).enqueue(object : Callback<List<DatasPedidosDto>> {
                override fun onResponse(call: Call<List<DatasPedidosDto>>, response: Response<List<DatasPedidosDto>>) {
                    if (response.isSuccessful) {
                        val resposta = response.body()
                        if (!resposta.isNullOrEmpty()) {
                            println(resposta)
                            val dataString = resposta.lastOrNull()?.datasPedidos
                            if (dataString != null) {
                                getProximoPedido(userId, dataString)
                            }
                        } else {
                            println("Lista de datas de pedidos vazia ou nula")
                        }
                    } else {
                        println("Erro na resposta do getDatasPedidos: $response")
                    }
                }

                override fun onFailure(call: Call<List<DatasPedidosDto>>, t: Throwable) {
                    println("Erro ao obter datas de pedidos: $t")
                }
            })

    }

    private fun getProximoPedido( userId: Int, dataEntrega: String) {
//        val pedido = PedidoDto(null, null, dataEntrega);
        pedidosApiService.getProximoPedido(userId, dataEntrega).enqueue(object : Callback<PedidoByDataDto> {
            override fun onResponse(call: Call<PedidoByDataDto>, response: Response<PedidoByDataDto>) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (resposta != null) {
                        screenDataDto = resposta
                        // Faça algo com os dados do pedido aqui
                    } else {
                        println("Resposta do getProximoPedido nula")
                    }
                } else {
                    println("Erro na resposta do getProximoPedido: $response")
                }
            }

            override fun onFailure(call: Call<PedidoByDataDto>, t: Throwable) {
                println("Erro ao obter próximo pedido: $t")
            }
        })
    }

//    private fun mapDatasPedidosToDataEntrega(datasPedidosDto: DatasPedidosDto): DataEntregaDto {
//        return DataEntregaDto(LocalDate.parse(datasPedidosDto.datasPedidos))
//    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Greeting(name: String, screenDataDto: PedidoByDataDto?, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current
    val qtdPorcoes = null
    val categorias: String
    val categoriasUnicas = mutableSetOf<String>()


    screenDataDto?.listaReceitas?.forEach{ it ->
        qtdPorcoes?.plusAssign(it.qtd_porcoes)
    }

    // Itera sobre cada receita para extrair suas categorias
    screenDataDto?.listaReceitas?.forEach { receita ->
        receita.categorias.forEach { categoria ->
            categoriasUnicas.add(categoria.nome)
        }
    }
    // Cria uma string com todas as categorias únicas
    categorias = categoriasUnicas.joinToString(", ")

    val dataFormatada = screenDataDto?.let { converterDataParaFormatoDescritivo(LocalDate.parse(it.dataEntrega)) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(249, 251, 251))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Pedido", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                Color(4, 93, 83),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        )

        //RecipeCardPedido()

        Spacer(modifier = Modifier.height(15.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(1.dp)
        ) {
            drawRoundRect(
                color = Color(160, 160, 160), cornerRadius = CornerRadius(0f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = R.mipmap.setaesquerda),
                contentDescription = "Seta Esquerda"
            )


            Card(
                modifier = modifier
                    .height(110.dp)
                    .width(90.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(255, 241, 221)
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color(255, 159, 28)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        dataFormatada?.diaDaSemanaAbreviado ?: "Sex", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                            Color(4, 93, 83),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "${dataFormatada?.numeroDoDia ?: "4"}", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                            Color(4, 93, 83),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontSize = 22.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        "${dataFormatada?.nomeDoMesAbreviado ?: "AGO"}", modifier = Modifier.fillMaxWidth(), style = TextStyle(
                            Color(4, 93, 83),
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    )
                }

            }

            Image(
                painter = painterResource(id = R.mipmap.setadireita),
                contentDescription = "Seta Direita"
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(225, 240, 239)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Entrega  -  ${screenDataDto?.logradouro ?: "Rua Haddock Lobo, 595"}", style = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        "${categorias} CATEGORIAS\n"
                                + "${screenDataDto?.listaReceitas?.size} Receitas \n"
                                + "${qtdPorcoes} Porções",
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                        )
                    )


            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                },
                modifier = Modifier.width(300.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp, pressedElevation = 4.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(243, 140, 0), contentColor = Color.White
                )
            ) {
                Text(
                    "Confirmar Entrega", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {

                },
                modifier = Modifier.width(300.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp, pressedElevation = 4.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(105, 160, 155), contentColor = Color.White
                )
            ) {
                Text(
                    "Pular Entrega", style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Receitas da Entrega", modifier = Modifier.fillMaxWidth(0.75f), style = TextStyle(
                Color(4, 93, 83),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        RecipeCardPedido()
    }
}

@Composable
fun RecipeCardPedido() {
    val recipeName = "Nome da Receita"
    val recipeTime = "30 min"
    val recipeCategory = "Categoria"

    val corHexadecimal = Color(0xFFFFF)
    val corPref = Color(0xFFFFF)

    Column(
        modifier = Modifier
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(corHexadecimal, RoundedCornerShape(8.dp))
            .padding(16.dp)
            .fillMaxWidth(0.8f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Imagem da Receita",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = recipeName, fontSize = 24.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_tempo_receita),
                contentDescription = "Imagem da Receita",
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "1 Hora e 30 minutos",
                fontSize = 16.sp,
                color = Color.Black,
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "| Carnes",
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(14.dp))

        Column(
            modifier = Modifier
                .border(
                    width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Preferência",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Avaliação da Receita",
            fontSize = 16.sp,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TelaPedidoPreview() {
    CulinartTheme {
        Greeting("Android", null)
    }
}