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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import sptech.culinart.ui.theme.CulinartTheme

class PerfilEnderecos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPerfilEnderecos("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPerfilEnderecos(name: String, modifier: Modifier = Modifier) {

    Column (modifier = Modifier.fillMaxSize().background(Color.White)){
        
        ComponenteHeader(name = "Android", modifier = Modifier.zIndex(99f))

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(.8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Perfil",
                    style = TextStyle(
                        Color(4, 93, 83),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Meus Endereços",
                    style = TextStyle(
                        Color(220, 119, 38),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(android.graphics.Color.parseColor("#C1CECD")))
                )

                Spacer(modifier = Modifier.height(55.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            Color(220, 119, 38),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(Color(255, 237, 211)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .padding(top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Rua Haddock Lobo, 595",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.icon_endereco_ativo),
                            contentDescription = "Icone de endereço ativo",
                            modifier = Modifier
                                .size(25.dp, 25.dp),
                            tint = Color(android.graphics.Color.parseColor("#DC7726"))
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                    ) {
                        Text(
                            text = "Cerqueira César",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.Black
                            )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 1.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "São Paulo - SP",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                            )

                            Text(
                                text = "01414-001",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }

                Spacer(modifier = Modifier.height(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            Color(0, 0, 0),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                            .padding(top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Av. Paulista, 854",
                            style = TextStyle(
                                Color(99, 99, 99),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(.95f)
                    ) {
                        Text(
                            text = "Bela Vista",
                            style = TextStyle(
                                Color(99, 99, 99),
                                fontSize = 16.sp
                            )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 1.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "São Paulo - SP",
                                style = TextStyle(
                                    Color(99, 99, 99),
                                    fontSize = 16.sp
                                )
                            )

                            Text(
                                text = "01310-913",
                                style = TextStyle(
                                    Color(99, 99, 99),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
                Spacer(modifier = Modifier.height(205.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaPerfilEnderecosPreview() {
    CulinartTheme {
        TelaPerfilEnderecos("Android")
    }
}