package sptech.culinart.api.data.assinatura

import java.time.LocalDate

data class PagamentoDTO(
    val idTransacao : Int,
    val statusTransacao : String,
    val linkCobranca : String,
)
