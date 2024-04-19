package sptech.culinart.model

data class LoginResponse(
    val userId: Int,
    val userEmail: String,
    val userTelefone: String,
    val userName: String,
    val jwtToken: String
)
