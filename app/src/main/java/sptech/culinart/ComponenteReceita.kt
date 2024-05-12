package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import sptech.culinart.api.data.ingrediente.IngredienteDTO
import sptech.culinart.api.data.modoPreparo.ModoPreparoDTO
import sptech.culinart.api.data.receita.ReceitaExibicaoDTO
import sptech.culinart.ui.theme.CulinartTheme

class ComponenteReceita : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receita = intent.getSerializableExtra("receita") as? ReceitaExibicaoDTO
        val qtdPorcoes = intent.getIntExtra("qtdPorcoes", 0)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (receita != null) {
                        ComponenteReceita(receita = receita, qtdPorcoes = qtdPorcoes)
                    }
                }
            }
        }
    }
}

@Composable
fun ComponenteReceita(receita: ReceitaExibicaoDTO, qtdPorcoes: Int, modifier: Modifier = Modifier) {
    var selectedTabIndex = remember { mutableStateOf(0) }
    Column {


        ComponenteHader("Android", modifier = Modifier.zIndex(99f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp, bottom = 30.dp)
                .zIndex(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = receita.nome,
                style = TextStyle(
                    Color(220, 119, 30),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .width(320.dp),
                horizontalAlignment = Alignment.End
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        modifier = Modifier
                            .width(105.dp)
                            .padding(end = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "${receita.mediaAvaliacoes}",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraLight
                            ),
                            modifier = Modifier.padding(end = 1.5.dp)
                        )
                        Text(
                            text = "(${receita.qtdAvaliacoes})",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.ExtraLight
                            ),
                            modifier = Modifier.padding(end = 1.5.dp)
                        )
                    }

                    val fullStars = receita.mediaAvaliacoes.toInt()
                    val halfStar = receita.mediaAvaliacoes - fullStars >= 0.5f

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        content = {
                            repeat(fullStars) {
                                item {
                                    Icon(
                                        modifier = Modifier.width(14.dp).height(14.dp),
                                        painter = painterResource(id = R.drawable.icon_estrela_inteira_avaliacao),
                                        contentDescription = "Estrela da avaliação de receita",
                                        tint = Color(android.graphics.Color.parseColor("#FCDC7C"))
                                    )
                                }
                            }
                            if (halfStar) {
                                item {
                                    Icon(
                                        modifier = Modifier.width(14.dp).height(14.dp).padding(end = 5.dp),
                                        painter = painterResource(id = R.drawable.icon_meia_estrela_avaliacao),
                                        contentDescription = "Meia estrela da avaliação de receita",
                                        tint = Color(android.graphics.Color.parseColor("#FCDC7C"))
                                    )
                                }
                            }
                        }
                    )
                }


                AsyncImage(
                    model = "https://drive.google.com/thumbnail?id=" + receita.imagem,
                    contentDescription = "Imagem da Receita",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .padding(4.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                )

                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,

                        ) {
                            Text(
                                text = "Preferências:",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                ),
                                modifier = Modifier.padding(end = 2.5.dp)
                            )
                        }
                    }
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



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 11.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Tempo de preparo:",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        ),
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    Text(
                        text = "${receita.horas} Hora e ${receita.minutos} minutos",
                        style = TextStyle(fontSize = 16.sp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(top = 15.dp)
                    .shadow(elevation = 3.dp, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp)
                    .size(width = 320.dp, height = 355.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            if (dragAmount > 0) {
                                selectedTabIndex.value = 0
                            } else if (dragAmount < 0) {
                                selectedTabIndex.value = 1
                            }
                        }
                    }
            ) {
                if (selectedTabIndex.value == 0) {
                    CardIngredientes(receita.ingredientes, qtdPorcoes)
                } else {
                    CardModoDePreparo(receita.modoPreparos)
                }
            }

        }
    }

}


@Composable
fun CardIngredientes(listaReceitaIndrediente: List<IngredienteDTO> = listOf(), qtdPorcoes : Int) {
    val ingredientes = listOf(
        "300 gramas de alcatra",
        "½ cebola pequena",
        "2 dentes de alho",
        "½ pera",
        "2 cebolinhas",
        "1  colher de sopa de açúcar mascavo",
        "40 mililitros de molho shoyu",
        "1 colher de sopa de óleo de gergelim",
        "1 colher de sopa de sementes de gergelim",
        "pimenta do reino à gosto",
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, top = 5.dp)
    ) {
        Text(
            text = "Ingredientes",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#DC7726"))
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(listaReceitaIndrediente) { ingrediente ->
                Text(
                    text = "•  ${ingrediente.quantidade} ${ingrediente.unidadeMedidaEnum}(s) (${ingrediente.nome})",
                    style = TextStyle(
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.padding(start = 2.dp, top = 1.6.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(qtdPorcoes >= 1) {
                Text(
                    text = "Ingredientes para render ${qtdPorcoes} porções",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                )
            }
            Row(
                modifier = Modifier
                    .width(90.dp)
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(1.75.dp)
                        .background(Color(android.graphics.Color.parseColor("#959595")))
                )

                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(1.75.dp)
                        .background(Color(android.graphics.Color.parseColor("#E2E2E2")))
                )
            }
        }
    }
}

@Composable
fun CardModoDePreparo(passosModoPreparo: List<ModoPreparoDTO>) {
    val passos = listOf(
        "Em uma tigela grande, misture todos os ingredientes da marinada.",
        "Adicione a carne e misture bem para que todos os pedaços estejam cobertos pela marinada.",
        "Leve à geladeira por pelo menos 30 minutos, ou até 24 horas",
        "Aqueça uma frigideira ou grelha em fogo médio-alto.",
        "Retire a carne da marinada e reserve a marinada.",
        "Grelhe a carne por 2-3 minutos de cada lado, ou até que esteja cozida ao ponto desejado.",
        "Sirva imediatamente com arroz branco, legumes e uma guarnição de sua escolha.",
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 3.dp)
    ) {
        Text(
            text = "Modo de preparo",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#DC7726"))
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            itemsIndexed(passosModoPreparo) { index, passo ->
                Text(
                    text = "${index + 1}. ${passo.passo}",
                    style = TextStyle(
                        fontSize = 16.sp,
                    ),
                    modifier = Modifier.padding(start = 2.dp, top = 1.2.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .width(90.dp)
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(1.dp)
                        .background(Color(android.graphics.Color.parseColor("#E2E2E2")))
                )

                Box(
                    modifier = Modifier
                        .width(42.dp)
                        .height(1.dp)
                        .background(Color(android.graphics.Color.parseColor("#959595")))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponenteReceitaPreview() {
    CulinartTheme {
        val receita = ReceitaExibicaoDTO(
            id = 1,
            nome = "Bulgi",
            horas = 1,
            minutos = 20,
            descricao = "Descrição da receita",
            imagem = "imagem_url",
            mediaAvaliacoes = 4.5,
            qtdAvaliacoes = 100,
            ingredientes = listOf(),
            modoPreparos = listOf(),
            categorias = listOf(),
            preferencias = listOf(),
            avaliacoes = listOf(),

        )
        ComponenteReceita(receita = receita, qtdPorcoes = 4)
    }
}