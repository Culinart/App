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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sptech.culinart.ui.theme.CulinartTheme

class ComponenteReceita : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComponenteReceita("Android")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponenteReceita(name: String, modifier: Modifier = Modifier){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 68.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Bulgogi",
            style = TextStyle(
                Color(220, 119, 30),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Column (
            modifier = Modifier
                .width(200.dp),
            horizontalAlignment = Alignment.End
        ){
            Row(
                modifier = Modifier
                    .width(105.dp)
                    .padding(end = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ){
                Text(text = "5.0",
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight.ExtraLight
                    ),
                    modifier = Modifier.padding(end = 1.5.dp)
                )
                Text(text = "(100)",
                    style = TextStyle(
                        fontSize = 8.sp,
                        fontWeight = FontWeight.ExtraLight
                    ),
                    modifier = Modifier.padding(end = 1.5.dp)
                )
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.estrela_avaliacao_12dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.estrela_avaliacao_12dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.estrela_avaliacao_12dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.estrela_avaliacao_12dp),
                        contentDescription = "Estrela da avaliação de receita",
                        tint = Color(android.graphics.Color.parseColor("#FCDC7C")).copy(0.8f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.estrela_avaliacao_12dp),
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
                    .clip(RoundedCornerShape(10.dp))
                    .padding(top = 1.2.dp),
                contentScale = ContentScale.FillBounds
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text ="Categoria:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.5.sp
                    ),
                    modifier = Modifier.padding(end = 2.5.dp)
                )
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(12.dp)
                        .border(
                            width = 0.25.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(5.dp)
                        )
                        .background(
                            color = Color(android.graphics.Color.parseColor("#001581")).copy(0.26f),
                            shape = RoundedCornerShape(5.dp)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Koreana",
                        style = TextStyle(
                            fontSize = 7.sp
                        )
                    )
                }
            }


            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 11.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text ="Tempo de preparo:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 8.5.sp
                    ),
                    modifier = Modifier.padding(end = 2.dp)
                )
                Text(
                    text = "1 hora e 20 minutos",
                    style = TextStyle(fontSize = 8.5.sp)
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