package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

                    TelaPreferencias(screenDataDto, screenDataDtoUsuario)
                }
            }
        }
    }

    fun getPreferenciasUsuario(){
        val prefsManager = PreferencesManager.getInstance(this)
        val userId = prefsManager.getUserId()

        preferenciaUsuarioApiService.getPreferenciasUsuario(userId).enqueue(object :
            retrofit2.Callback<List<UsuarioPreferenciaDTO>> {
            override fun onResponse(call: Call<List<UsuarioPreferenciaDTO>>, response: Response<List<UsuarioPreferenciaDTO>>) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (!resposta.isNullOrEmpty()) {
                        println("Resposta: "+resposta)
                        screenDataDtoUsuario.postValue(resposta!!)

                    } else {
                        println("Lista preferencias vazia ou nula")
                    }
                } else {
                    println("Erro na resposta do getPreferencias: $response")
                }
            }

            override fun onFailure(call: Call<List<UsuarioPreferenciaDTO>>, t: Throwable) {
                println("Erro ao obter datas de pedidos: $t")
            }
        })
    }
    fun getPreferencias(){
        preferenciaApiService.getAllPreferencias().enqueue(object :
            retrofit2.Callback<List<PreferenciasDTO>> {
            override fun onResponse(call: Call<List<PreferenciasDTO>>, response: Response<List<PreferenciasDTO>>) {
                if (response.isSuccessful) {
                    val resposta = response.body()
                    if (!resposta.isNullOrEmpty()) {
                        println("Resposta: "+resposta)
                        screenDataDto.postValue(resposta!!)

                    } else {
                        println("Lista preferencias vazia ou nula")
                    }
                } else {
                    println("Erro na resposta do getPreferencias: $response")
                }
            }

            override fun onFailure(call: Call<List<PreferenciasDTO>>, t: Throwable) {
                println("Erro ao obter datas de pedidos: $t")
            }
        })
    }
}

val preferencias = listOf(
    "Doce", "Salgado", "Neutro", "Picante", "Amargo", "Ácido", "Arabe", "Brazil"
)


@Composable
fun TelaPreferencias(screenDataDtoRemember: MutableLiveData<List<PreferenciasDTO>>, screenDataDtoUsuarioRemember: MutableLiveData<List<UsuarioPreferenciaDTO>>) {
    Column {
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
                text = "Preferências",
                color = Color(0xFF045D53),
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))

        }
        Column(
            Modifier.padding(horizontal = 40.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        1.dp, Color(0xFFFFA500), shape = RoundedCornerShape(8.dp)
                    )
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
                    for (i in preferencias.indices step 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (i < preferencias.size) {
                                    screenDataDtoUsuarioRemember.value?.get(i)
                                        ?.let { Preferencia(it.preferencias) }
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (i + 1 < preferencias.size) {
                                    screenDataDtoUsuarioRemember.value?.get(i)
                                        ?.let { Preferencia(it.preferencias) }
                                }
                            }
                        }
                    }
                }
            }
            Button(
                onClick = { /* Ação ao clicar no botão */ },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFFF9F1C)),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Editar",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Column {
                Text(
                    text = "Preferências",
                    color = Color(0xFF045D53),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Divider(
                    color = Color.Black, thickness = 1.dp, modifier = Modifier.fillMaxWidth(1f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                val preferenciasPorLinha = 3
                val totalPreferencias = preferencias.size

                for (i in preferencias.indices step preferenciasPorLinha) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        for (j in i until i + preferenciasPorLinha) {
                            if (j < totalPreferencias) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    screenDataDtoRemember.value?.let { Preferencia( it.get(j)) } /*A preferencia da api tem que ser inserida aqui*/
                                    Spacer(modifier = Modifier.height(14.dp))
                                }
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
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
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color(0xFFFFA500), shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "teste",
                color = Color(0xFF045D53),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Cor Fundo: #${"ffffff"}",
                color = Color(0xFF045D53),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Cor Texto: #${"000000"}",
                color = Color(0xFF045D53),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(14.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPreferenciasPreview() {
    TelaPreferencias(screenDataDtoRemember = MutableLiveData(), screenDataDtoUsuarioRemember = MutableLiveData())
}
