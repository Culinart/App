package sptech.culinart.api.data.preferencia

import java.io.Serializable

data class PreferenciasDTO(
    var id: Int,
    var nome: String,
    var tipoPreferenciaEnum: TipoPreferenciaEnum,
    var corFundo: String,
    var corTexto: String,
): Serializable
