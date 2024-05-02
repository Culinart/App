package sptech.culinart.api.data.usuario

data class UsuarioTokenDTO(
    val userID: Int,
    val nome: String,
    val email: String,
    val token: String,
    val isAtivo: String,
    val permissao: String
)
