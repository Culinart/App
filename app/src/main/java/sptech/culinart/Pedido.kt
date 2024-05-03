package sptech.culinart

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.core.content.ContextCompat.startActivity
import androidx.core.i18n.DateTimeFormatter
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImage
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.pedido.DataEntregaDto
import sptech.culinart.api.data.pedido.DatasPedidosDTO

import sptech.culinart.api.data.pedido.PedidoByDataDto
import sptech.culinart.api.data.pedido.PedidoDto
import sptech.culinart.api.data.receita.ReceitaExibicaoPedidoDto
import sptech.culinart.api.data.usuario.UsuarioTokenDTO
import sptech.culinart.api.utils.converterDataParaFormatoDescritivo
import sptech.culinart.ui.theme.CulinartTheme
import java.time.LocalDate


class Pedido : ComponentActivity() {
    private val pedidosApiService = RetrofitInstace.getPedidosApiService()
//    private var screenDataDto: PedidoByDataDto? = null
    private var screenDataDto = MutableLiveData<PedidoByDataDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    runBlocking {
                        async { getDatasPedidos() }.await()
                    }
                    Greeting(screenDataDto, { getDatasPedidos() })
                }
            }

        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getDatasPedidos() {
        val prefsManager = PreferencesManager.getInstance(this)
        val userId = prefsManager.getUserId()
        val token = prefsManager.getToken()

            pedidosApiService.getDatasPedidos(userId).enqueue(object : Callback<List<DatasPedidosDTO>> {
                override fun onResponse(call: Call<List<DatasPedidosDTO>>, response: Response<List<DatasPedidosDTO>>) {
                    if (response.isSuccessful) {
                        val resposta = response.body()
                        if (!resposta.isNullOrEmpty()) {
                            println("Resposta: "+resposta)
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

                override fun onFailure(call: Call<List<DatasPedidosDTO>>, t: Throwable) {
                    println("Erro ao obter datas de pedidos: $t")
                }
            })

    }

    private fun getProximoPedido( userId: Int, dataEntrega: String) {
        pedidosApiService.getProximoPedido(userId, dataEntrega).enqueue(object : Callback<PedidoByDataDto> {
            override fun onResponse(call: Call<PedidoByDataDto>, response: Response<PedidoByDataDto>) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (resposta != null) {
                        screenDataDto.postValue(resposta!!)
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


@Composable
fun Greeting(
    screenDataDtoRemember: MutableLiveData<PedidoByDataDto>,
    getDatasPedidos: () -> Unit,
    modifier: Modifier = Modifier,
    dataObtida: Boolean = false
) {
    val pedidosApiService = RetrofitInstace.getPedidosApiService()
    val contexto = LocalContext.current
    var qtdPocoesTotal = 0
    val categorias: String
    val categoriasUnicas = mutableSetOf<String>()
    val dataObtidaApi = remember {
        mutableStateOf(dataObtida)
    }

    if(screenDataDtoRemember.observeAsState().value != null) {

        val screenDataDto = screenDataDtoRemember.observeAsState().value

        println("ScreenDataDto: $screenDataDto")

//        screenDataDto?.listaReceitas?.forEach { it ->
//            qtdPorcoes.plusAssign(it.qtdPorcoes)
//        }

        // Itera sobre cada receita para extrair suas categorias
        screenDataDto?.listaReceitas?.forEach { receita ->
            qtdPocoesTotal = screenDataDto.listaReceitas.sumOf { it.qtdPorcoes ?: 0 }

            receita.categorias.forEach { categoria ->
                categoriasUnicas.add(categoria.nome)
            }
        }
        // Cria uma string com todas as categorias únicas
        categorias = categoriasUnicas.joinToString(", ")

        val dataFormatada =
            screenDataDto?.let { converterDataParaFormatoDescritivo(LocalDate.parse(it.dataEntrega)) }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(249, 251, 251))
                .fillMaxSize(),
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
                            dataFormatada?.diaDaSemanaAbreviado ?: "Sex",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                Color(4, 93, 83),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            "${dataFormatada?.numeroDoDia ?: "4"}",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                Color(4, 93, 83),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            "${dataFormatada?.nomeDoMesAbreviado ?: "AGO"}",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
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
                        "Entrega  -  ${screenDataDto?.logradouro ?: "Rua Haddock Lobo, 595"}",
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                    Text(
                        "${categorias}\n"
                                + "${screenDataDto?.listaReceitas?.size} Receitas \n"
                                + "${qtdPocoesTotal} Porções",
                        style = TextStyle(
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    )


                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {

                        if (screenDataDto != null) {
                            pedidosApiService.putPedidoConcluido(screenDataDto.id).enqueue(object : Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    if (response.isSuccessful) {
                                        getDatasPedidos()

                                    } else {
                                        println("Deu ruim")
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    println(t)
                                }
                            })
                        }


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
                            fontWeight = FontWeight.Bold, fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {

                        if (screenDataDto != null) {
                            pedidosApiService.putPedidoCancelado(screenDataDto.id).enqueue(object : Callback<Void> {
                                override fun onResponse(
                                    call: Call<Void>,
                                    response: Response<Void>
                                ) {
                                    if (response.isSuccessful) {
                                        getDatasPedidos()

                                    } else {
                                        println("Deu ruim")
                                    }
                                }

                                override fun onFailure(call: Call<Void>, t: Throwable) {
                                    println(t)
                                }
                            })
                        }

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
            if (screenDataDto != null) {
                RecipeCard(receitas = screenDataDto.listaReceitas)
            } else {
                Text(
                    text = "Nenhuma receita encontrada!",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        Color(4, 93, 83),
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                )
            }
        }
    }
}

@Composable
fun RecipeCard(receitas: List<ReceitaExibicaoPedidoDto>) {

    if (receitas.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nenhuma receita encontrada!",
                color = Color.Gray,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }
    } else {
        LazyColumn {
            items(receitas) { receita ->
                Column(
                    modifier = Modifier
                        .padding(36.dp)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(6.dp))
                        .background(Color.White)
                        .padding(18.dp)
                        .fillMaxWidth()
                ) {

                    AsyncImage(
                        model = "https://drive.google.com/thumbnail?id=" + receita.imagem,
                        contentDescription = "Imagem da Receita",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(175.dp)
                            .padding(4.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = receita.nome,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
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
                        if (receita.horas == 1) {
                            Text(
                                text = "1 Hora e " + receita.minutos + " minutos",
                                fontSize = 16.sp,
                                color = Color.Black,
                            )
                        } else {
                            Text(
                                text = "" + receita.horas + " Horas e " + receita.minutos + " minutos",
                                fontSize = 16.sp,
                                color = Color.Black,
                            )
                        }

                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "| " + receita.categorias.get(0).nome,
                            fontSize = 10.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyRow {
                        items(receita.preferencias) { preferencia ->
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .background(
                                        color = Color(
                                            android.graphics.Color.parseColor(
                                                "#" + preferencia.corFundo
                                            )
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {

                                Text(
                                    text = preferencia.nome,
                                    fontSize = 12.sp,
                                    color = Color(
                                        android.graphics.Color.parseColor(
                                            "#" + preferencia.corTexto
                                        )
                                    ),
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row {
                            Image(
                                painter = painterResource(id = R.drawable.icon_star_receita),
                                contentDescription = "Icone de estrela de avaliação",
                                modifier = Modifier
                                    .width(10.dp)
                                    .height(10.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(3.dp))
//                        Text(
//                            text = "" + receita.mediaAvaliacoes + " (" + receita.qtdAvaliacoes + " avaliações)",
//                            fontSize = 16.sp,
//                            color = Color.Black,
//                            fontWeight = FontWeight.Light
//                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPedidoPreview() {
    CulinartTheme {
        Greeting(screenDataDtoRemember = MutableLiveData<PedidoByDataDto>(), { Unit })
    }
}