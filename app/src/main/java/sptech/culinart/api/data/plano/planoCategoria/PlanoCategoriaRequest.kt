package sptech.culinart.api.data.plano.planoCategoria

import androidx.compose.runtime.snapshots.SnapshotStateList

data class PlanoCategoriaRequest(
    val planoId: Int,
    val categoriaId: List<CategoriaId>
)
