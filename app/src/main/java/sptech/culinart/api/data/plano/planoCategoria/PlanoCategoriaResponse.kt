package sptech.culinart.api.data.plano.planoCategoria

import sptech.culinart.api.data.plano.PlanoResponseDTO

data class PlanoCategoriaResponse(
    val id: Int,
    val plano: PlanoResponseDTO,
    val categoria: CategoriaExibicaoDTO
)
