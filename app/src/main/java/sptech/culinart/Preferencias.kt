package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.preferencia.PreferenciasDTO
import sptech.culinart.api.data.usuario.UsuarioPreferenciaDTO
import sptech.culinart.ui.theme.CulinartTheme

class Preferencias : ComponentActivity() {
    private val preferenciaApiService = RetrofitInstace.getPreferenciaApiService()
    private val preferenciaUsuarioApiService = RetrofitInstace.getPreferenciaUsuarioApiService()
    private var screenDataDto = MutableLiveData<List<PreferenciasDTO>>()
    private var screenDataDtoUsuario = MutableLiveData<List<UsuarioPreferenciaDTO>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    getPreferencias()
                    getPreferenciasUsuario()
                    TelaPreferencias(screenDataDto, screenDataDtoUsuario)
                }
            }
        }
    }

    private fun getPreferenciasUsuario() {
        val prefsManager = PreferencesManager.getInstance(this)
        val userId = prefsManager.getUserId()

        preferenciaUsuarioApiService.getPreferenciasUsuario(userId).enqueue(object :
            retrofit2.Callback<List<UsuarioPreferenciaDTO>> {
            override fun onResponse(call: Call<List<UsuarioPreferenciaDTO>>, response: Response<List<UsuarioPreferenciaDTO>>) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (!resposta.isNullOrEmpty()) {
                        screenDataDtoUsuario.postValue(resposta!!)
                    } else {
                        println("Lista preferencias vazia ou nula")
                    }
                } else {
                    println("Erro na resposta do getPreferenciasUsuario: $response")
                }
            }

            override fun onFailure(call: Call<List<UsuarioPreferenciaDTO>>, t: Throwable) {
                println("Erro ao obter preferencias do usuário: $t")
            }
        })
    }

    private fun getPreferencias() {
        preferenciaApiService.getAllPreferencias().enqueue(object :
            retrofit2.Callback<List<PreferenciasDTO>> {
            override fun onResponse(call: Call<List<PreferenciasDTO>>, response: Response<List<PreferenciasDTO>>) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (!resposta.isNullOrEmpty()) {
                        screenDataDto.postValue(resposta!!)
                    } else {
                        println("Lista preferencias vazia ou nula")
                    }
                } else {
                    println("Erro na resposta do getPreferencias: $response")
                }
            }

            override fun onFailure(call: Call<List<PreferenciasDTO>>, t: Throwable) {
                println("Erro ao obter preferencias: $t")
            }
        })
    }
}

@Composable
fun TelaPreferencias(
    screenDataDtoRemember: MutableLiveData<List<PreferenciasDTO>>,
    screenDataDtoUsuarioRemember: MutableLiveData<List<UsuarioPreferenciaDTO>>
) {
    val preferencias by screenDataDtoRemember.observeAsState(emptyList())
    val preferenciasUsuario by screenDataDtoUsuarioRemember.observeAsState(emptyList())

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        item {
            // Top Bar
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
        }

        item {
            // Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Preferências",
                    color = Color(0xFF045D53),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        item {
            // Chosen Preferences
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFFFFA500), shape = RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Preferências Escolhidas",
                        color = Color(0xFF045D53),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    for (i in preferenciasUsuario.indices step 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (i < preferenciasUsuario.size) {
                                    Preferencia(preferencia = preferenciasUsuario[i].preferencia)
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (i + 1 < preferenciasUsuario.size) {
                                    Preferencia(preferencia = preferenciasUsuario[i + 1].preferencia)
                                }
                            }
                        }
                    }
                }
            }
        }
        val preferenciasUsuarioIds = preferenciasUsuario.map { it.preferencia.id }
        val preferenciasFiltradas = preferencias.filter { it.id !in preferenciasUsuarioIds }

        items(preferenciasFiltradas.chunked(2)) { preferenciasPorLinha ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                preferenciasPorLinha.forEach { preferencia ->
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Preferencia(preferencia = preferencia)
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }

                // Adicionar um espaço vazio se houver apenas uma preferência na linha
                if (preferenciasPorLinha.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }


        }
    }





@Composable
fun Preferencia(preferencia: PreferenciasDTO) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(android.graphics.Color.parseColor("#${preferencia.corFundo}"))),
            //.border(1.dp, Color(0xFF000000), shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = preferencia.nome,
            color = Color(android.graphics.Color.parseColor("#${preferencia.corTexto}")),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun TelaPreferenciasPreview() {
    TelaPreferencias(screenDataDtoRemember = MutableLiveData(), screenDataDtoUsuarioRemember = MutableLiveData())
}
