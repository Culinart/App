package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sptech.culinart.ui.theme.CulinartTheme

class ComponenteReceita : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ComponenteReceita("Android")
                }
            }
        }
    }
}

@Composable
fun ComponenteReceita(name: String, modifier: Modifier = Modifier){
    Header()
    Spacer(modifier = Modifier.height(25.dp))
    Column {
        
    }
}

@Preview(showBackground = true)
@Composable
fun ComponenteReceitaPreview() {
    CulinartTheme {
        ComponenteReceita("Android")
    }
}