package sptech.culinart.api.data.ingrediente

data class IngredienteDTO(
    var id: Int,
    var nome: String,
    var quantidade: Double,
    var unidadeMedidaEnum: UnidadeMedidaEnum
)
