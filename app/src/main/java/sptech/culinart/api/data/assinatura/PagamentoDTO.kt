package sptech.culinart.api.data.assinatura

data class PagamentoDTO(
    val idTransacao : Int,
    val statusTransacao : String,
    val linkCobranca : String,
)
