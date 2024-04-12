package sptech.culinart.api.data.usuario

data class UsuarioCriacaoDTO(
    val nome: String,
    val email: String,
    val senha: String,
    val cpf: String,
    val telefone: String
)