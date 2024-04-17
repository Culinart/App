package sptech.culinart.api.data.receita

import sptech.culinart.api.data.categoria.CategoriaCardDto
import sptech.culinart.api.data.preferencia.PreferenciaCardDto

data class ReceitaExibicaoPedidoDto (
    val id: Int,
    val nome: String,
    val horas: Int,
    val minutos: Int,
    val qtd_porcoes: Int,
    val preferencias: List<PreferenciaCardDto>,
    val categorias: List<CategoriaCardDto>
)