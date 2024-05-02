package sptech.culinart.api.data.usuario

import androidx.annotation.Size
import org.intellij.lang.annotations.Pattern

data class UsuarioCriacaoDTO(
    @field:Size(min = 3)
    val nome: String,
    @field:Pattern(".+@.+\\..+")
    val email: String,
    @field:Size(min = 3)
    val senha: String,
    val cpf: String,
    val telefone: String
)