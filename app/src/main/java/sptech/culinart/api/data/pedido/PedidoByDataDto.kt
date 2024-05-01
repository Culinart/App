package sptech.culinart.api.data.pedido

import androidx.compose.runtime.MutableState
import sptech.culinart.api.data.receita.ReceitaExibicaoPedidoDto

data class PedidoByDataDto (
    val id: Int,
    val valor: Double,
    val status: String,
    val dataEntrega: String,
    val logradouro: String,
    val numero: Int,
    val listaReceitas: List<ReceitaExibicaoPedidoDto>
)