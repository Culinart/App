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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            BasicTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(32.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(16.dp))
                    .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {

            }
        }
        Column(
            Modifier.padding(horizontal = 40.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Preferências",
                    color = Color(0xFF3F4747),
                    fontSize = 16.sp,
                )
                Image(
                    painter = painterResource(id = R.drawable.icon_edit_preferencia),
                    contentDescription = "Imagem da Receita",
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Preferencia(cor = "#FF0000", preferencia = "Neutro")
                Preferencia(cor = "#FF0010", preferencia = "Preferencia")
            }
        }
        RecipeCard()
    }
}

@Composable
fun Preferencia(cor: String, preferencia: String) {
    val corPreferencia = Color(android.graphics.Color.parseColor(cor))
    Box(
        modifier = Modifier
            .background(color = corPreferencia, shape = RoundedCornerShape(8.dp))
    ) {
        Text(
            text = "$preferencia",
            fontSize = 16.sp,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}




@Composable
fun RecipeCard() {
    val recipeName = "Nome da Receita"
    val recipeTime = "30 min"
    val recipeCategory = "Categoria"

    val corHexadecimal = Color(0xFFFFF)
    val corPref = Color(0xFFFFF)

    Column(
        modifier = Modifier
            .padding(36.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
            .background(corHexadecimal, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "Imagem da Receita",
            modifier = Modifier
                .fillMaxWidth()
                .height(175.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = recipeName,
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

        //Preferencia(cor = "fff", preferencia = "Neutro") /* quando essa linha entra da erro*/

        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(color = Color.White, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "Preferência",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            )
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
                        .width(16.dp)
                        .height(16.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(6.dp))
                Image(
                    painter = painterResource(id = R.drawable.icon_star_receita),
                    contentDescription = "Icone de estrela de avaliação",
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))

            }
            Spacer(modifier = Modifier.width(130.dp))
            Image(
                painter = painterResource(id = R.drawable.icon_add_receita),
                contentDescription = "Icone de adicionar receita",
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CulinartTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            TelaReceitas()
        }
    }
}