package sptech.culinart.api.data.pedido

import java.time.LocalDate



data class PedidoDto(

    var id: Int?,
    var dataCriacao: LocalDate?,
//    var valor: Double? = null
//    var status: StatusPedidoEnum? = null
    var dataEntrega: LocalDate?
//    var plano: Plano? = null
//    var listaReceitas: List<Receita>? = null
//    var endereco: Endereco? = null
)
