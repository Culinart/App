package sptech.culinart

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImage
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.pedido.DatasPedidosDto
import sptech.culinart.api.data.pedido.PedidoByDataDto
import sptech.culinart.api.data.pedido.ReceitaPedidoDto
import sptech.culinart.api.data.receita.ReceitaDTO
import sptech.culinart.api.data.receita.ReceitaExibicaoDTO
import sptech.culinart.api.data.receita.ReceitaExibicaoPedidoDto
import sptech.culinart.ui.theme.CulinartTheme
import java.io.Serializable

class Receitas : ComponentActivity() {
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
                    println("Entrou aqui")
                    runBlocking {
                        async { getDatasPedido() }.await()
                    }
                    TelaReceitas(screenDataDto) { getDatasPedido() }
                }
            }

        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getDatasPedido() {
        val prefsManager = PreferencesManager.getInstance(this)
        val userId = 3
        val token = prefsManager.getToken()

        pedidosApiService.getDatasPedidos(userId).enqueue(object : Callback<List<DatasPedidosDto>> {
            override fun onResponse(
                call: Call<List<DatasPedidosDto>>,
                response: Response<List<DatasPedidosDto>>
            ) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (!resposta.isNullOrEmpty()) {
                        println("Resposta: " + resposta)
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

    private fun getProximoPedido(userId: Int, dataEntrega: String) {
        pedidosApiService.getProximoPedido(userId, dataEntrega)
            .enqueue(object : Callback<PedidoByDataDto> {
                override fun onResponse(
                    call: Call<PedidoByDataDto>,
                    response: Response<PedidoByDataDto>
                ) {
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
fun TelaReceitas(
    screenDataDtoRemember: MutableLiveData<PedidoByDataDto>,
    getDatasPedidosDto: () -> Unit
) {
    val screenDataDto = screenDataDtoRemember.observeAsState().value

    println("ScreenDataDto: $screenDataDto")

    Column(
        modifier = Modifier
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFF00AE9E))
                .padding(30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.lista),
                contentDescription = "Imagem da Receita",
                modifier = Modifier
                    .size(40.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Culinart",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Receitas",
                color = Color(0xFF045D53),
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))

            var pesquisaText = remember { mutableStateOf("") }
            var receitas = remember { mutableStateOf<List<ReceitaDTO>>(emptyList()) }


            TextField(
                value = pesquisaText.value,
                onValueChange = {
                    pesquisaText.value = it
                    if (it.isNotBlank()) {
                        ObterReceitasDaApiPorTermo(pesquisaText.value) { receitasDaApi ->
                            receitas.value = receitasDaApi
                        }
                    } else {
                        ObterReceitasDaApi { receitasDaApi ->
                            receitas.value = receitasDaApi
                        }
                    }
                },
                label = { Text("Pesquisar") },
                placeholder = { Text("Ex: Bolo de Chocolate") },
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
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search Icon",
                        tint = Color(4, 93, 83) // Cor do ícone
                    )
                }
            )
            if (receitas.value.isEmpty()) {
                ObterReceitasDaApi { receitasDaApi ->
                    receitas.value = receitasDaApi
                }
            }

            if (screenDataDto != null) {
                ReceitaCard(receitas = receitas.value, pedido = screenDataDto)
            }
        }
    }
}


fun ObterReceitasDaApiPorTermo(termo: String, onReceitasCarregadas: (List<ReceitaDTO>) -> Unit) {
    val receitaApiService = RetrofitInstace.getReceitasApiService()

    receitaApiService.getReceitasPorTermo(termo).enqueue(object : Callback<List<ReceitaDTO>> {
        override fun onResponse(
            call: Call<List<ReceitaDTO>>,
            response: Response<List<ReceitaDTO>>
        ) {
            if (response.isSuccessful) {
                val receitasDTO = response.body() ?: emptyList()
                onReceitasCarregadas(receitasDTO)
            } else {
                println("Requisição falhou!")
            }
        }

        override fun onFailure(call: Call<List<ReceitaDTO>>, t: Throwable) {
            println("Conexão com o Back-end falhou!")
        }
    })
}


fun ObterReceitasDaApi(onReceitasCarregadas: (List<ReceitaDTO>) -> Unit) {
    val receitaApiService = RetrofitInstace.getReceitasApiService()

    receitaApiService.getReceitas().enqueue(object : Callback<List<ReceitaDTO>> {
        override fun onResponse(
            call: Call<List<ReceitaDTO>>,
            response: Response<List<ReceitaDTO>>
        ) {
            if (response.isSuccessful) {
                val receitasDTO = response.body() ?: emptyList()
                onReceitasCarregadas(receitasDTO)
            } else {
                println("Requisição falhou!")
            }
        }

        override fun onFailure(call: Call<List<ReceitaDTO>>, t: Throwable) {
            println("Conexão com o Back-end falhou!")
        }
    })
}

@Composable
fun ReceitaCard(receitas: List<ReceitaDTO>, pedido: PedidoByDataDto) {
    val contexto = LocalContext.current
    val pedidosApiService = RetrofitInstace.getPedidosApiService()

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
                            .clip(shape = RoundedCornerShape(8.dp))
                            .clickable(onClick = {
                                val receitaApiService = RetrofitInstace.getReceitasApiService()
                                receitaApiService
                                    .getOneReceita(receita.id)
                                    .enqueue(object : Callback<ReceitaExibicaoDTO> {
                                        override fun onResponse(
                                            call: Call<ReceitaExibicaoDTO>,
                                            response: Response<ReceitaExibicaoDTO>
                                        ) {
                                            if (response.isSuccessful) {
                                                println("Resposta do getOneReceita com sucesso: $response")
                                                val receitaFull = response.body()
                                                val infosReceita = Intent(
                                                    contexto,
                                                    ComponenteReceita::class.java
                                                ).apply {
                                                    putExtra(
                                                        "receita",
                                                        receitaFull as Serializable
                                                    )

                                                }
                                                contexto.startActivity(infosReceita)
                                            } else {
                                                error("Erro na resposta do getOneReceita: $response")
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<ReceitaExibicaoDTO>,
                                            t: Throwable
                                        ) {
                                            println("Erro ao obter getOneReceita: $t")
                                        }
                                    })
                            }),
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
                            text = "| " + receita.categorias.get(0).categoria.nome,
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
                                                "#" + preferencia.preferencia.corFundo
                                            )
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    )
                            ) {

                                Text(
                                    text = preferencia.preferencia.nome,
                                    fontSize = 12.sp,
                                    color = Color(
                                        android.graphics.Color.parseColor(
                                            "#" + preferencia.preferencia.corTexto
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
                        Text(
                            text = "" + receita.mediaAvaliacoes + " (" + receita.qtdAvaliacoes + " avaliações)",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Light
                        )
                        Spacer(modifier = Modifier.width(80.dp))
                        val pedidoActivity = Intent(contexto, Pedido::class.java)
                        if (pedido.listaReceitas.any { it.id == receita.id }) {
                            Image(
                                painter = painterResource(id = R.drawable.icon_check_receita),
                                contentDescription = "Icone de receita já adicionada",
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                                    .clickable {
                                        pedidosApiService
                                            .deleteReceitaPedido(receita.id, pedido.id)
                                            .enqueue(object : Callback<Void> {
                                                override fun onResponse(
                                                    call: Call<Void>,
                                                    response: Response<Void>
                                                ) {
                                                    if (response.isSuccessful) {
                                                        println("Receita removida com sucesso!")
                                                        contexto.startActivity(pedidoActivity)
                                                    } else {
                                                        println("Erro ao remover receita!")
                                                        println(response.message())
                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<Void>,
                                                    t: Throwable
                                                ) {
                                                    println("Erro ao remover receita do pedido!")
                                                }
                                            })
                                    },
                                contentScale = ContentScale.Crop,
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.icon_add_receita),
                                contentDescription = "Icone de adicionar receita",
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                                    .clickable {
                                        pedidosApiService
                                            .adcionarReceitaPedido(
                                                ReceitaPedidoDto(
                                                    receita.id,
                                                    pedido.id
                                                )
                                            )
                                            .enqueue(object : Callback<Void> {
                                                override fun onResponse(
                                                    call: Call<Void>,
                                                    response: Response<Void>
                                                ) {
                                                    if (response.isSuccessful) {
                                                        println("Receita adcionada com sucesso!")
                                                        contexto.startActivity(pedidoActivity)
                                                    } else {
                                                        println("Erro ao remover receita!")
                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<Void>,
                                                    t: Throwable
                                                ) {
                                                    println("Erro ao adcionar receita ao pedido!")
                                                }
                                            })
                                    },
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TelaReceitasPreview() {
    CulinartTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TelaReceitas(screenDataDtoRemember = MutableLiveData<PedidoByDataDto>(), { Unit })
        }
    }
}