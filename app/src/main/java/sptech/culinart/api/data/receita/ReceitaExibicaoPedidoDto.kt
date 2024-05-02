package sptech.culinart.api.data.receita

import com.google.gson.annotations.SerializedName
import sptech.culinart.api.data.categoria.CategoriaCardDto
import sptech.culinart.api.data.preferencia.PreferenciaCardDto

data class ReceitaExibicaoPedidoDto (
    val id: Int,
    val nome: String,
    val horas: Int,
    val minutos: Int,
    @SerializedName("qtd_porcoes")
    val qtdPorcoes: Int,
    val imagem: String,
    val preferencias: List<PreferenciaCardDto>,
    val categorias: List<CategoriaCardDto>,
    @SerializedName("media_notas")
    val mediaNotas: Double,
    @SerializedName("qtd_avaliacoes")
    val qtdAvaliacoes: Int
)