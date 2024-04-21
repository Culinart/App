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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.receita.ReceitasDTO
import sptech.culinart.ui.theme.CulinartTheme

class Receitas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    TelaReceitas()
                }
            }
        }
    }
}

@Composable
fun TelaReceitas() {
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
                text = "Receitas",
                color = Color(0xFF045D53),
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))

            var pesquisaText = remember { mutableStateOf("") }

            BasicTextField(
                value = pesquisaText,
                onValueChange = { pesquisaText = it },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(32.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            )

        }
        var receitas = remember { mutableStateOf<List<ReceitasDTO>>(emptyList()) }

        // Obter receitas da API
        ObterReceitasDaApi { receitasDaApi ->
            receitas.value = receitasDaApi
        }

        RecipeCard(receitas = receitas.value)
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var text = remember { mutableStateOf("") }

    Surface(
        color = Color.LightGray,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            BasicTextField(
                value = text,
                onValueChange = { newText ->
                    text = newText
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Button(
                onClick = { onSearch(text) },
                modifier = Modifier
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
            ) {
                Text(text = "Search")
            }
        }
    }
}

@Composable
fun ObterReceitasDaApi(onReceitasCarregadas: (List<ReceitasDTO>) -> Unit) {
    val receitaApiService = RetrofitInstace.getReceitasApiService()

    receitaApiService.getReceitas().enqueue(object : Callback<List<ReceitasDTO>> {
        override fun onResponse(
            call: Call<List<ReceitasDTO>>,
            response: Response<List<ReceitasDTO>>
        ) {
            if (response.isSuccessful) {
                val receitasDTO = response.body() ?: emptyList()
                onReceitasCarregadas(receitasDTO)
            } else {
                println("Deu ruim aqui!")
            }
        }

        override fun onFailure(call: Call<List<ReceitasDTO>>, t: Throwable) {
            println("Deu ruim")
        }
    })
}

@Composable
fun RecipeCard(receitas: List<ReceitasDTO>) {
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
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "Imagem da Receita",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(175.dp)
                        .padding(4.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = receita.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
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
                                    "#" + receita.preferencias.get(
                                        0
                                    ).preferencia.corFundo
                                )
                            ),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    receita.preferencias.forEach { preferencia ->
                        Text(
                            text = preferencia.preferencia.nome,
                            fontSize = 12.sp,
                            color = Color(android.graphics.Color.parseColor("#" + preferencia.preferencia.corTexto)),
                            modifier = Modifier.padding(4.dp))
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
                    Image(
                        painter = painterResource(id = R.drawable.icon_add_receita),
                        contentDescription = "Icone de adicionar receita",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp),
                        contentScale = ContentScale.Crop
                    )
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
            TelaReceitas()
        }
    }
}