package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.culinart.api.data.pedido.DataEntregaDto
import sptech.culinart.api.data.pedido.DatasPedidosDto
import sptech.culinart.api.data.pedido.PedidoByDataDto

interface PedidoApiService {

    @GET("api/pedidos/datas/{idUser}")
    fun getDatasPedidos(@Path("idUser") idUser: Int): Call<List<DatasPedidosDto>>

    @POST("api/pedidos/entrega/{idUser}")
    fun getProximoPedido(@Path("idUser") idUser: Int, @Body data: DataEntregaDto): Call<PedidoByDataDto>
}