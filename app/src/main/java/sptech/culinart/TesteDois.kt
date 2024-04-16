package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sptech.culinart.ui.theme.CulinartTheme

class TesteDois : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComponenteTesteDois("Android")
                }
            }
        }
    }
}

@Composable
fun ComponenteTesteDois(name: String, modifier: Modifier = Modifier){
    val passos = listOf(
        "Em uma tigela grande, misture todos os ingredientes da marinada.",
        "Adicione a carne e misture bem para que todos os pedaços estejam cobertos pela marinada.",
        "Leve à geladeira por pelo menos 30 minutos, ou até 24 horas",
        "Aqueça uma frigideira ou grelha em fogo médio-alto.",
        "Retire a carne da marinada e reserve a marinada.",
        "Grelhe a carne por 2-3 minutos de cada lado, ou até que esteja cozida ao ponto desejado.",
        "Sirva imediatamente com arroz branco, legumes e uma guarnição de sua escolha.",
    )
    var contadorDePassos = remember { mutableStateOf(0) }
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 237.dp)
            .border(
                width = 0.25.dp,
                color = Color.Black,
                shape = RoundedCornerShape(2.dp)
            ),
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp, top = 3.dp)
        ){
            Text(
                text = "Modo de preparo",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color =  Color(android.graphics.Color.parseColor("#DC7726"))
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
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 3.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    modifier = Modifier
                        .width(72.dp)
                        .padding(top = 17.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(1.75.dp)
                            .background(Color.Gray.copy(.35f))
                    )

                    Box(
                        modifier = Modifier
                            .width(32.dp)
                            .height(1.75.dp)
                            .background(Color.DarkGray.copy(.65f))

                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ComponenteTesteDoisPreview() {
    CulinartTheme {
        ComponenteTesteDois("Android")
    }
}