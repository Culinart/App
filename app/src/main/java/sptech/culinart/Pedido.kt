package sptech.culinart

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sptech.culinart.ui.theme.CulinartTheme

class Pedido : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(249, 251, 251))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Pedido",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                Color(4, 93, 83),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 25.sp
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(1.dp)
        ) {
            drawRoundRect(
                color = Color(160, 160, 160),
                cornerRadius = CornerRadius(0f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){

            Image(painter = painterResource(id = R.mipmap.setaesquerda), contentDescription = "Seta Esquerda")

            Card (modifier = modifier
                .height(110.dp)
                .width(90.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(255, 241, 221)
                ),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color(255, 159, 28)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                    Column( modifier = modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Sex",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                Color(4, 93, 83),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            "4",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                Color(4, 93, 83),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            "AGO",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                Color(4, 93, 83),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        )
                    }

            }

            Image(painter = painterResource(id = R.mipmap.setadireita), contentDescription = "Seta Direita")

        }

        Spacer(modifier = Modifier.height(30.dp))

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(225, 240, 239)),
                horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.75f)
            ){

                Spacer(modifier = Modifier.height(10.dp))

                Text("Entrega  -  Rua Haddock Lobo, 595",
                    style = TextStyle(
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    ))

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    "Carnes, Peixes, Rápido e Fácil\n" +
                            "3 Receitas\n" +
                            "15 Porções",
                    style = TextStyle(
                        textAlign = TextAlign.Start,
                    )
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick =
                {

                },
                modifier = Modifier.width(300.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(243,140,0),
                    contentColor = Color.White
                )
            ) {
                Text("Confirmar Entrega",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick =
                {

                },
                modifier = Modifier.width(300.dp),
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 4.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(105,160,155),
                    contentColor = Color.White
                )
            ) {
                Text("Pular Entrega",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Receitas da Entrega",
            modifier = Modifier.fillMaxWidth(0.75f),
            style = TextStyle(
                Color(4, 93, 83),
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(15.dp))

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CulinartTheme {
        Greeting("Android")
    }
}