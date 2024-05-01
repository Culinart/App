package sptech.culinart.api.data.categoria

import java.math.BigDecimal

data class CategoriaDTO(
    var id: Int,
    var nome: String,
    var valor: BigDecimal
)
