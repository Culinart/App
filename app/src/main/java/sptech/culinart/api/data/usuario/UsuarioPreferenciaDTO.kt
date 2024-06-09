package sptech.culinart.api.data.usuario

import sptech.culinart.api.data.preferencia.PreferenciasDTO

data class UsuarioPreferenciaDTO (
    val id: Int,
    val preferencia: PreferenciasDTO
)