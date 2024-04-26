package sptech.culinart

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.categoria.Categoria
import sptech.culinart.api.data.plano.PlanoRequestDTO
import sptech.culinart.api.data.plano.PlanoResponseDTO
import sptech.culinart.api.data.plano.planoCategoria.CategoriaId
import sptech.culinart.api.data.plano.planoCategoria.PlanoCategoriaRequest
import sptech.culinart.api.data.plano.planoCategoria.PlanoCategoriaResponse
import sptech.culinart.ui.theme.CulinartTheme

class CadastroPlano : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        setContent {
            CulinartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaCadastroPlano(extras)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun TelaCadastroPlano(extras: Bundle?, modifier: Modifier = Modifier) {

    val contexto = LocalContext.current

    val prefsManager = PreferencesManager.getInstance(contexto);
    val userId = prefsManager.getUserId()

    val callPlanoCat = remember {
        mutableStateOf(false)
    }


    val categorias = remember { mutableStateListOf<Categoria>() }

    val categoriasSelecionadas = remember { mutableStateListOf<Categoria>() }

    val categoriasSelecionadasIds = remember { mutableStateListOf<CategoriaId>() }

    val selectedDay = remember { mutableStateOf("") }

    val numeroPessoas = remember { mutableStateOf(0) }

    val planoId = remember { mutableStateOf(0) }

    val numeroRefeicoesDia = remember { mutableStateOf(0) }

    val numeroDiasPorSemana = remember { mutableStateOf(0) }

    val isCarnesClicked = remember { mutableStateOf(false) }

    val isVegetarianoClicked = remember { mutableStateOf(false) }

    val isPescetarianoClicked = remember { mutableStateOf(false) }

    val isVeganoClicked = remember { mutableStateOf(false) }

    val isRapidoFacilClicked = remember { mutableStateOf(false) }

    val isFitSaudavelClicked = remember { mutableStateOf(false) }

    val horarios = listOf(
        "06:00", "07:00", "08:00", "09:00", "10:00", "11:00",
        "12:00", "13:00", "14:00", "15:00", "16:00", "17:00",
        "18:00", "19:00", "20:00", "21:00", "22:00"
    )
    val selectedHorario = remember { mutableStateOf("") }
    val expanded = remember { mutableStateOf(false) }

    val valorPlano = remember { mutableStateOf(0.0) }

    val selectedMaiorPrecoCategoria = remember { mutableStateOf(0.0) }

    val erroApi = remember { mutableStateOf("") }


    val apiCategoria = RetrofitInstace.getApiCategoriaService()
    val call = apiCategoria.getCategorias()
    val erroApiCat = remember { mutableStateOf("") }
    call.enqueue(object : Callback<List<Categoria>> {
        override fun onResponse(call: Call<List<Categoria>>, response: Response<List<Categoria>>) {
            if (response.isSuccessful) {
                println("Entrou no sucess")

                response.body()?.let {
                    categorias.clear()
                    categorias.addAll(it)
                }

                println("CATEGORIAS: ")
                categorias.forEach { categoria ->
                    println("Cateogira ID: ${categoria.id}, Categoria: ${categoria.nome}, Preço: ${categoria.valor}")
                }

            } else {
                println("Erro ao trazer as categorias")
                erroApiCat.value = "Erro ao trazer as categorias"
            }
        }
        override fun onFailure(call: Call<List<Categoria>>, t: Throwable) {
            println("Entrou no failure")
            erroApiCat.value = "Falha na conexão: ${t.message}"
        }
    })


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(249, 251, 251))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.cadastrosegundaetapa),
            contentDescription = "Cadastro etapa de Endereço",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Personalize seu Plano!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                Color(255, 159, 28),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            "Categorias",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                Color(4, 93, 83),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row {

            Spacer(modifier = Modifier.weight(0.4f))

            Card (modifier = modifier
                .height(100.dp)
                .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = if (isCarnesClicked.value) Color(255, 241, 221) else Color.White
                ),
                border = if (isCarnesClicked.value) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(2.dp, Color(228, 228, 228)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                TextButton(onClick = {
                    isCarnesClicked.value = !isCarnesClicked.value
                    if (isCarnesClicked.value) {
                        categorias.firstOrNull { it.nome.equals("Carnes", ignoreCase = true) }?.let {
                            if (!categoriasSelecionadas.contains(it)) {
                                categoriasSelecionadas.add(it)
                            }
                        }
                    } else {
                        categoriasSelecionadas.removeAll { it.nome.equals("Carnes", ignoreCase = true) }
                    }
                }) {
                    Column( modifier = modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.mipmap.iconecarnes), contentDescription = "Ícone de Carnes")
                        Text(
                            "Carnes",
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

            }

            Spacer(modifier = Modifier.weight(0.4f))

            Card (modifier = modifier
                .height(100.dp)
                .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = if (isVegetarianoClicked.value) Color(255, 241, 221) else Color.White
                ),
                border = if (isVegetarianoClicked.value) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(2.dp, Color(228, 228, 228)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                TextButton(onClick = {
                    isVegetarianoClicked.value = !isVegetarianoClicked.value
                    if (isVegetarianoClicked.value) {
                        categorias.firstOrNull { it.nome.equals("Vegetariano", ignoreCase = true) }?.let {
                            if (!categoriasSelecionadas.contains(it)) {
                                categoriasSelecionadas.add(it)
                            }
                        }
                    } else {
                        categoriasSelecionadas.removeAll { it.nome.equals("Vegetariano", ignoreCase = true) }
                    }
                }) {
                    Column( modifier = modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.mipmap.iconevegetariano), contentDescription = "Ícone de Vegetariano")
                        Text(
                            "Vegetariano",
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
            }


            Spacer(modifier = Modifier.weight(0.4f))

        }

        Spacer(modifier = Modifier.height(30.dp))

        Row {

            Spacer(modifier = Modifier.weight(0.4f))

            Card (modifier = modifier
                .height(100.dp)
                .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = if (isPescetarianoClicked.value) Color(255, 241, 221) else Color.White
                ),
                border = if (isPescetarianoClicked.value) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(2.dp, Color(228, 228, 228)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                TextButton(onClick = {
                    isPescetarianoClicked.value = !isPescetarianoClicked.value
                    if (isPescetarianoClicked.value) {
                        categorias.firstOrNull { it.nome.equals("Pescetariano", ignoreCase = true) }?.let {
                            if (!categoriasSelecionadas.contains(it)) {
                                categoriasSelecionadas.add(it)
                            }
                        }
                    } else {
                        categoriasSelecionadas.removeAll { it.nome.equals("Pescetariano", ignoreCase = true) }
                    }
                }) {
                    Column( modifier = modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.mipmap.iconepescetariano), contentDescription = "Ícone pescetariano")
                        Text(
                            "Pescetariano",
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
            }

            Spacer(modifier = Modifier.weight(0.4f))

            Card (modifier = modifier
                .height(100.dp)
                .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = if (isVeganoClicked.value) Color(255, 241, 221) else Color.White
                ),
                border = if (isVeganoClicked.value) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(2.dp, Color(228, 228, 228)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                TextButton(onClick = {
                    isVeganoClicked.value = !isVeganoClicked.value
                    if (isVeganoClicked.value) {
                        categorias.firstOrNull { it.nome.equals("Vegano", ignoreCase = true) }?.let {
                            if (!categoriasSelecionadas.contains(it)) {
                                categoriasSelecionadas.add(it)
                            }
                        }
                    } else {
                        categoriasSelecionadas.removeAll { it.nome.equals("Vegano", ignoreCase = true) }
                    }
                }) {
                    Column( modifier = modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.mipmap.iconevegano), contentDescription = "Ícone vegano")
                        Text(
                            "Vegano",
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
            }


            Spacer(modifier = Modifier.weight(0.4f))

        }


        Spacer(modifier = Modifier.height(30.dp))

        Row {

            Spacer(modifier = Modifier.weight(0.4f))

            Card(
                modifier = Modifier
                    .height(100.dp)
                    .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = if (isRapidoFacilClicked.value) Color(255, 241, 221) else Color.White
                ),
                border = if (isRapidoFacilClicked.value) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(2.dp, Color(228, 228, 228)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                TextButton(onClick = {
                    isRapidoFacilClicked.value = !isRapidoFacilClicked.value
                    if (isRapidoFacilClicked.value) {
                        categorias.firstOrNull { it.nome.equals("Rápido e Fácil", ignoreCase = true) }?.let {
                            if (!categoriasSelecionadas.contains(it)) {
                                categoriasSelecionadas.add(it)
                            }
                        }
                    } else {
                        categoriasSelecionadas.removeAll { it.nome.equals("Rápido e Fácil", ignoreCase = true) }
                    }
                }) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.iconerapidoefacil),
                            contentDescription = "Ícone rápido e fácil"
                        )
                        Text(
                            "Rápido e Fácil",
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = Color(4, 93, 83),
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.weight(0.4f))

            Card (modifier = modifier
                .height(100.dp)
                .weight(1f),
                colors = CardDefaults.cardColors(
                    containerColor = if (isFitSaudavelClicked.value) Color(255, 241, 221) else Color.White
                ),
                border = if (isFitSaudavelClicked.value) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(2.dp, Color(228, 228, 228)),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ){
                TextButton(onClick = {
                    isFitSaudavelClicked.value = !isFitSaudavelClicked.value
                    if (isFitSaudavelClicked.value) {
                        categorias.firstOrNull { it.nome.equals("Fit e Saudável", ignoreCase = true) }?.let {
                            if (!categoriasSelecionadas.contains(it)) {
                                categoriasSelecionadas.add(it)
                            }
                        }
                    } else {
                        categoriasSelecionadas.removeAll { it.nome.equals("Fit e Saudável", ignoreCase = true) }
                    }
                }) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(painter = painterResource(id = R.mipmap.iconefitesaudavel), contentDescription = "Ícone fit e saudável")
                        Text(
                            "Fit e Saudável",
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
            }


            Spacer(modifier = Modifier.weight(0.4f))

        }

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            "Quantidades",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                Color(4, 93, 83),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Pessoas",
                style = TextStyle(
                    Color(220,119,38),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.weight(5f))

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            (1..8).forEach { number ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors =  CardDefaults.cardColors(
                        containerColor = if (numeroPessoas.value == number) Color(255, 241, 221) else Color.White
                    ),
                    shape = RoundedCornerShape(topStart = if (number == 1) 8.dp else 0.dp,
                        bottomStart = if (number == 1) 8.dp else 0.dp,
                        topEnd = if (number == 8) 8.dp else 0.dp,
                        bottomEnd = if (number == 8) 8.dp else 0.dp),
                    border = if (numeroPessoas.value == number) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(1.dp, Color(175, 175, 175))
                ) {
                    Column (modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        TextButton(onClick = { numeroPessoas.value = number }
                        ) {
                            Text(text = number.toString(), color = Color.Black)
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Refeições Por Dia",
                style = TextStyle(
                    Color(220,119,38),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.weight(5f))

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            (1..6).forEach { number ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors =  CardDefaults.cardColors(
                        containerColor = if (numeroRefeicoesDia.value == number) Color(255, 241, 221) else Color.White
                    ),
                    shape = RoundedCornerShape(topStart = if (number == 1) 8.dp else 0.dp,
                        bottomStart = if (number == 1) 8.dp else 0.dp,
                        topEnd = if (number == 8) 8.dp else 0.dp,
                        bottomEnd = if (number == 8) 8.dp else 0.dp),
                    border = if (numeroRefeicoesDia.value == number) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(1.dp, Color(175, 175, 175))
                ) {
                    Column (modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        TextButton(onClick = { numeroRefeicoesDia.value = number }
                        ) {
                            Text(text = number.toString(), color = Color.Black)
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Dias Por Semana",
                style = TextStyle(
                    Color(220,119,38),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.weight(5f))

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            (1..7).forEach { number ->
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors =  CardDefaults.cardColors(
                        containerColor = if (numeroDiasPorSemana.value == number) Color(255, 241, 221) else Color.White
                    ),
                    shape = RoundedCornerShape(topStart = if (number == 1) 8.dp else 0.dp,
                        bottomStart = if (number == 1) 8.dp else 0.dp,
                        topEnd = if (number == 8) 8.dp else 0.dp,
                        bottomEnd = if (number == 8) 8.dp else 0.dp),
                    border = if (numeroDiasPorSemana.value == number) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(1.dp, Color(175, 175, 175))
                ) {
                    Column (modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(align = Alignment.CenterVertically),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        TextButton(onClick = { numeroDiasPorSemana.value = number }
                        ) {
                            Text(text = number.toString(), color = Color.Black)
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Dia para a Entrega",
                style = TextStyle(
                    Color(220,119,38),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.weight(5f))

        }

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(20.dp))

            val days = listOf("SEGUNDA", "TERCA", "QUARTA", "QUINTA", "SEXTA")
            days.forEach { day ->
                val dayInitial = day[0].toString()
                Card(
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp),
                    shape = RoundedCornerShape(50),
                    colors =  CardDefaults.cardColors(
                        containerColor = if (selectedDay.value == day) Color(255, 241, 221) else Color.White
                    ),
                    border = if (selectedDay.value == day) BorderStroke(2.dp, Color(255, 159, 28)) else BorderStroke(1.dp, Color(140, 140, 140))
                ) {
                    TextButton(onClick = { selectedDay.value = day }) {
                        Text(text = dayInitial, color = Color.Black)
                    }
                }
            }

            Spacer(modifier = Modifier.width(20.dp))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Horário para a Entrega",
                style = TextStyle(
                    Color(220,119,38),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    fontSize = 18.sp
                )
            )

            Spacer(modifier = Modifier.weight(5f))

        }

        Spacer(modifier = Modifier.height(15.dp))

        Column {
            Column(
                modifier = Modifier
                    .width(150.dp)
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                Box() {
                    TextButton(onClick = { expanded.value = !expanded.value }) {

                        Text(
                            text = if (selectedHorario.value.isNotEmpty()) selectedHorario.value else "Horário",
                            color = if (selectedHorario.value.isNotEmpty()) Color.Black else Color.Gray
                        )
                    }

                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    modifier = Modifier
                        .background(Color.White)
                        .border(1.dp, Color(160, 160, 160))
                        .height(300.dp)
                ) {
                    horarios.forEach { horario ->
                        DropdownMenuItem(
                            text = { Text(horario, color = Color.Black) },
                            onClick = {
                                selectedHorario.value = horario
                                expanded.value = false
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(1.dp)
                        .background(color = Color.Black)
                )
            }
        }



        Spacer(modifier = Modifier.height(40.dp))


        Button(
            onClick =
            {
                if(
                    isCarnesClicked.value || isVegetarianoClicked.value ||
                    isPescetarianoClicked.value || isVeganoClicked.value ||
                    isRapidoFacilClicked.value || isFitSaudavelClicked.value &&
                    selectedDay.value.isNotBlank() && numeroPessoas.value > 0 &&
                    numeroRefeicoesDia.value > 0 && numeroDiasPorSemana.value > 0 &&
                    selectedHorario.value.isNotBlank()
                ) {



                    selectedMaiorPrecoCategoria.value = categoriasSelecionadas.maxOfOrNull { categoria -> categoria.valor } ?: 1.0

                    println("===============================================================")
                    println("CATEGORIAS: ")
                    categorias.forEach { categoria ->
                        println("Categoria: ${categoria.nome}, Preço: ${categoria.valor}")
                    }

                    println("numeroPessoas" + numeroPessoas.value)
                    println("numeroRefeicoesDia" + numeroRefeicoesDia.value)
                    println("numeroDiasPorSemana" + numeroDiasPorSemana.value)
                    println("MAIOR PRECO CATEGORIA" + selectedMaiorPrecoCategoria.value)
                    println("===============================================================")

                    valorPlano.value = (numeroPessoas.value * numeroRefeicoesDia.value * numeroDiasPorSemana.value * selectedMaiorPrecoCategoria.value).toDouble()




                    val apiPlano = RetrofitInstace.getApiPlanoService()
                    val planoRequestDTO = PlanoRequestDTO(
                        categoria = categoriasSelecionadas,
                        qtdPessoas = numeroPessoas.value,
                        qtdRefeicoesDia = numeroRefeicoesDia.value,
                        qtdDiasSemana = numeroDiasPorSemana.value,
                        valorPlano = valorPlano.value,
                        valorAjuste = 0.0,
                        horaEntrega = selectedHorario.value,
                        diaSemana = selectedDay.value,
                        isAtivo = "ATIVO"
                    )

                    val callPlano = apiPlano.cadastrarPlano(userId, planoRequestDTO)

                    callPlano.enqueue(object : Callback<PlanoResponseDTO> {
                        override fun onResponse(call: Call<PlanoResponseDTO>, response: Response<PlanoResponseDTO>) {
                            if (response.isSuccessful) {
                                println("Entrou no sucess do cadastroPlano")

                                    planoId.value= response.body()!!.id

                                println("PLANO ID: " + planoId.value)

                                categoriasSelecionadas.forEach { categoria ->
                                    categoriasSelecionadasIds.add(CategoriaId(categoria.id))
                                }


                                val planoCategoriaRequest = PlanoCategoriaRequest(
                                    planoId = planoId.value,
                                    categoriaId = categoriasSelecionadasIds,
                                )

                                val callPlanoCategoria = apiPlano.cadastrarPlanoCategoria(planoCategoriaRequest)

                                callPlanoCategoria.enqueue(object : Callback<List<PlanoCategoriaResponse>> {
                                    override fun onResponse(call: Call<List<PlanoCategoriaResponse>>, response: Response<List<PlanoCategoriaResponse>>) {
                                        if (response.isSuccessful) {
                                            println("Entrou no sucess do cadastroPlanoCategoria")

                                            println(response.body())

                                            val cadastroCheckout = Intent(contexto, CadastroCheckout::class.java)

                                            extras?.let {
                                                cadastroCheckout.putExtras(it)
                                            }
                                            cadastroCheckout.putExtra("valorPlano", valorPlano.value)

                                            contexto.startActivity(cadastroCheckout)


                                        } else {
                                            println("Erro ao cadastrar o planoCategoria")
                                            erroApi.value = "Erro ao cadastrar o planoCategoria"
                                        }
                                    }
                                    override fun onFailure(call: Call<List<PlanoCategoriaResponse>>, t: Throwable) {
                                        println("Entrou no failure no cadastro do planoCategoria")
                                        erroApiCat.value = "Falha na conexão: ${t.message}"
                                    }
                                })

                                //callPlanoCat.value = true


                            } else {
                                println("Erro ao cadastrar o plano")
                                erroApi.value = "Erro ao cadastrar o plano"
                            }
                        }
                        override fun onFailure(call: Call<PlanoResponseDTO>, t: Throwable) {
                            println("Entrou no failure no cadastro do Plano")
                            erroApiCat.value = "Falha na conexão: ${t.message}"
                        }
                    })



                }
                //else {
                //    Text("Porfavor preencha e selecione todos os campos", style = TextStyle(
                //        Color.Red
                //   ))
                //}

            },
            modifier = Modifier.width(250.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(255, 159, 28),
                contentColor = Color.White
            )
        ) {
            Text("Confirmar")
        }

        Spacer(modifier = Modifier.height(40.dp))

        if (callPlanoCat.value) {

            val apiPlano = RetrofitInstace.getApiPlanoService()

            val planoCategoriaRequest = PlanoCategoriaRequest(
                planoId = planoId.value,
                categoriaId = categoriasSelecionadasIds,
            )

            val callPlanoCategoria = apiPlano.cadastrarPlanoCategoria(planoCategoriaRequest)

            callPlanoCategoria.enqueue(object : Callback<List<PlanoCategoriaResponse>> {
                override fun onResponse(call: Call<List<PlanoCategoriaResponse>>, response: Response<List<PlanoCategoriaResponse>>) {
                    if (response.isSuccessful) {
                        println("Entrou no sucess do cadastroPlanoCategoria")

                        val cadastroCheckout = Intent(contexto, CadastroCheckout::class.java)

                        extras?.let {
                            cadastroCheckout.putExtras(it)
                        }
                        cadastroCheckout.putExtra("valorPlano", valorPlano.value)

                        contexto.startActivity(cadastroCheckout)


                    } else {
                        println("Erro ao cadastrar o planoCategoria")
                        erroApi.value = "Erro ao cadastrar o planoCategoria"
                    }
                }
                override fun onFailure(call: Call<List<PlanoCategoriaResponse>>, t: Throwable) {
                    println("Entrou no failure no cadastro do planoCategoria")
                    erroApiCat.value = "Falha na conexão: ${t.message}"
                }
            })
        }

    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun TelaCadastroPlanoPreview() {
    CulinartTheme {
        TelaCadastroPlano(null)
    }
}