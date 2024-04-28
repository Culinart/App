package sptech.culinart.api.data.pedido

import sptech.culinart.api.data.receita.ReceitaExibicaoPedidoDto
import java.time.LocalDate

data class PedidoByDataDto (
    val id: Int,
    val valor: Double,
    val status: String,
    val dataEntrega: String,
    val logradouro: String,
    val numero: Int,
    val listaReceitas: List<ReceitaExibicaoPedidoDto>
)