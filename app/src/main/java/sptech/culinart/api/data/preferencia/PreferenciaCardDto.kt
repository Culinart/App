package sptech.culinart.api.data.preferencia

import java.io.Serializable

data class PreferenciaCardDto(
    val nome: String,
    val corFundo: String,
    val corTexto: String
) : Serializable

