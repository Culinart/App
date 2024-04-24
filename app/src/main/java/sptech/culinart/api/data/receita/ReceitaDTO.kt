package sptech.culinart.api.data.receita

import sptech.culinart.api.data.categoria.ReceitaCategoriaDTO
import sptech.culinart.api.data.ingrediente.IngredienteDTO
import sptech.culinart.api.data.modoPreparo.ModoPreparoDTO
import sptech.culinart.api.data.preferencia.ReceitaPreferenciaDTO

data class ReceitaDTO(
    val id: Int,
    val nome: String,
    val horas: Int,
    val minutos: Int,
    val descricao: String,
    val imagem: String,
    val mediaAvaliacoes: Double,
    val qtdAvaliacoes: Int,
    val ingredientes: List<IngredienteDTO>,
    val modoPreparos: List<ModoPreparoDTO>,
    val categorias: List<ReceitaCategoriaDTO>,
    val preferencias: List<ReceitaPreferenciaDTO>
)
