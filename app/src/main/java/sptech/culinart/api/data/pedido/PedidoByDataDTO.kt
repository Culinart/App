package sptech.culinart.api.data.pedido

import java.time.LocalDate

data class PedidoByDataDTO(
    var id: Int,
    var valor: Double,
    var status: StatusPedidoEnum,
    var dataEntrega: LocalDate,
    var logradouro: String,
    var numero: Int
)
