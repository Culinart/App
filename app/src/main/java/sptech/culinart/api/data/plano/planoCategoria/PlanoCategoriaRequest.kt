package sptech.culinart.api.data.plano.planoCategoria

data class PlanoCategoriaRequest(
    val planoId: Int,
    val categoriaId: List<CategoriaId>
)
