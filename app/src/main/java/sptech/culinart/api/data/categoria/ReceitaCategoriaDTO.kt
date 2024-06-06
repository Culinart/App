package sptech.culinart.api.data.categoria

import java.io.Serializable

data class ReceitaCategoriaDTO(
    val id: Int,
    val categoria: CategoriaDTO
) : Serializable
