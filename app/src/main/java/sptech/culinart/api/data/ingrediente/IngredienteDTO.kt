package sptech.culinart.api.data.ingrediente

import java.io.Serializable

data class IngredienteDTO(
    var id: Int,
    var nome: String,
    var quantidade: Double,
    var unidadeMedidaEnum: UnidadeMedidaEnum
): Serializable
