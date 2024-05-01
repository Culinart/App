package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import sptech.culinart.api.data.pedido.DatasPedidosDto
import sptech.culinart.api.data.pedido.PedidoByDataDto

interface PedidoApiService {

    @GET("api/pedidos/datas/{idUser}")
    fun getDatasPedidos(/*@Header("Authorization") token: String,*/ @Path("idUser") idUser: Int): Call<List<DatasPedidosDto>>

    @POST("/api/pedidos/entrega/{idUser}")
    fun getProximoPedido(/*@Header("Authorization") token: String,*/ @Path("idUser") idUser: Int, @Body pedido: String): Call<PedidoByDataDto>


    @PUT("/api/pedidos/entregue/{idPedido}")
    fun putPedidoConcluido(@Path("idPedido") idPedido: Int): Call<Void>

    @PUT("/api/pedidos/pularEntrega/{idPedido}")
    fun putPedidoCancelado(@Path("idPedido") idPedido: Int): Call<Void>
}