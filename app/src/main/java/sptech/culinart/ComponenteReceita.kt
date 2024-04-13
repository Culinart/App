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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import sptech.culinart.ui.theme.CulinartTheme

class ComponenteReceita : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComponenteReceita("Android")
                }
            }
        }
    }
}

@Composable
fun ComponenteReceita(name: String, modifier: Modifier = Modifier) {
    var showIngredients = remember { mutableStateOf(true) }

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
            text = "Bulgogi",
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
            Row(
                modifier = Modifier
                    .width(105.dp)
                    .padding(end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ) {
                Text(
                    text = "5.0",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraLight
                    ),
                    modifier = Modifier.padding(end = 1.5.dp)
                )
                Text(
                    text = "(100)",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraLight
                    ),
                    modifier = Modifier.padding(end = 1.5.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_estrela_avaliacao_14dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_estrela_avaliacao_14dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_estrela_avaliacao_14dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_estrela_avaliacao_14dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.icon_estrela_avaliacao_14dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                }

            }
            Image(
                painter = painterResource(id = R.mipmap.img_receita),
                contentDescription = "Imagem de receita para o componente Receita",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(top = 1.2.dp),
                contentScale = ContentScale.FillBounds
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Categoria:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(end = 2.5.dp)
                )
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(20.dp)
                        .border(
                            width = 0.25.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(android.graphics.Color.parseColor("#001581")).copy(
                                0.26f
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Koreana",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
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
                    text = "1 hora e 20 minutos",
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
        ) {
            if (showIngredients.value) {
                CardIngredientes(onSwipe = { showIngredients.value = false })
            } else {
                CardModoDePreparo(onSwipe = { showIngredients.value = true })
            }
        }
    }
}


@Composable
fun CardIngredientes(onSwipe: () -> Unit) {
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
            items(ingredientes) { ingrediente ->
                Text(
                    text = "•  $ingrediente",
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
                .padding(top = 10.dp, end = 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ingredientes para render 4 porções",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )
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
fun CardModoDePreparo(onSwipe: () -> Unit) {
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
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = Color(android.graphics.Color.parseColor("#DC7726"))
            )
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(passos) { passo ->
                Text(
                    text = "•  $passo",
                    style = TextStyle(
                        fontSize = 7.sp,
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
                    .padding(top = 17.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(1.75.dp)
                        .background(Color(android.graphics.Color.parseColor("#E2E2E2")))
                )

                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(1.75.dp)
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
        ComponenteReceita("Android")
    }
}