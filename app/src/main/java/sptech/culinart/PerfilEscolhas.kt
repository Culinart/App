package sptech.culinart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sptech.culinart.ui.theme.CulinartTheme

class PerfilEscolhas : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CulinartTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    TelaPerfilEscolhas("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPerfilEscolhas(name: String, modifier: Modifier = Modifier){

}

@Preview(showBackground = true)
@Composable
fun TelaPerfilEscolhasPreview() {
    CulinartTheme {
        TelaCadastro("Android")
    }
}