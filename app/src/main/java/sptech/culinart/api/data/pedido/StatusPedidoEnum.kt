package sptech.culinart.api.data.pedido

enum class StatusPedidoEnum(val status: String) {
    PENDENTE("PENDENTE"),
    ATIVO("ATIVO"),
    ENTREGUE("ENTREGUE"),
    CANCELADO("CANCELADO")
}