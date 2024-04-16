package sptech.culinart.api.data.usuario

data class UsuarioExibicaoDTO(
    val id: Int,
    val nome: String,
    val email: String,
    val telefone: String,
    val cpf: String,
    val isAtivo: String,
    val permissao: String
)