package sptech.culinart.api.data.preferencia

data class PreferenciasDTO(
    var id: Int,
    var nome: String,
    var tipoPreferenciaEnum: String,
    var corFundo: String,
    var corTexto: String,
)
