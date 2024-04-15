package sptech.culinart

import android.content.Intent
import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sptech.culinart.ui.theme.CulinartTheme

class Header : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComponenteHader("Android")
                }
            }
        }
    }
}

@Composable
fun ComponenteHader(name: String, modifier: Modifier = Modifier) {
    val isColumnVisible = remember { mutableStateOf(false) }
    Text(text = "Texto")
    Row (
        modifier = modifier
            .background(Color(0, 174, 158))
            .fillMaxWidth()
            .height(68.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            painter = painterResource(id = R.mipmap.menu),
            contentDescription = "Menu",
            tint = Color.White,
            modifier = Modifier
                .fillMaxWidth(fraction = 0.3F)
                .size(35.dp, 35.dp)
                .padding(top = 5.dp)
                .clickable {
                    isColumnVisible.value = true
                }
        )
        Text("Culinart",
            style = TextStyle(
                Color(255 , 255, 255),
                fontSize = 25.sp
            ),
            modifier = Modifier.padding(end = 45.dp, top = 2.dp)
        )
    }
    if (isColumnVisible.value) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0, 174, 158)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                painter = painterResource(id = R.mipmap.menu),
                contentDescription = "Menu",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.3F)
                    .size(40.dp, 40.dp)
                    .padding(bottom = 15.dp)
                    .clickable {
                        isColumnVisible.value = false
                    }
            )

            Row(
                modifier = modifier
                    .width(150.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_pedidos_header),
                    contentDescription = "Icone de pedido do Header",
                    tint = Color.White,
                )

                Text(
                    text = "Pedidos",
                    style = TextStyle(
                        Color(255, 255, 255),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 8.5.dp, end=0.5.dp, top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = modifier
                    .width(150.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_receitas_header),
                    contentDescription = "Icone de receita do Header",
                    tint = Color.White
                )

                Text(
                    text = "Receitas",
                    style = TextStyle(
                        Color(255, 255, 255),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp, top = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = modifier
                    .width(150.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_meu_plano_header),
                    contentDescription = "Icone de Meu Plano do Header",
                    tint = Color.White,
                    modifier = Modifier.padding(start = 1.75.dp)
                )

                Text(
                    text = "Meu Plano",
                    style = TextStyle(
                        Color(255, 255, 255),
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(start = 11.25.dp)
                )

            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = modifier
                    .width(150.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_suporte_header),
                    contentDescription = "Icone de Suporte do Header",
                    tint = Color.White
                )

                Text(
                    text = "Suporte",
                    style = TextStyle(
                        Color(255, 255, 255),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }


            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = modifier
                    .width(150.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_user_header),
                    contentDescription = "Icone de Perfil do Header",
                    tint = Color.White
                )

                Text(
                    text = "Perfil",
                    style = TextStyle(
                        Color(255, 255, 255),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Row(
                modifier = modifier
                    .width(150.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.icon_logout_header),
                    contentDescription = "Icone de Logout do Header",
                    tint = Color.White,
                    modifier = Modifier.padding(start = 2.dp)
                )

                Text(
                    text = "Logout",
                    style = TextStyle(
                        Color(255, 255, 255),
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(start = 6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponenteHaderPreview() {
    CulinartTheme {
        ComponenteHader("Android")
    }
}