package sptech.culinart.api.data.preferencia

import java.io.Serializable

data class ReceitaPreferenciaDTO(
    val int: Int,
    val preferencia: PreferenciasDTO
) : Serializable
