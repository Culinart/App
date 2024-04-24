package sptech.culinart.api.endpoints

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.culinart.api.data.pedido.DatasPedidosDTO
import sptech.culinart.api.data.pedido.PedidoByDataDTO
import sptech.culinart.api.data.pedido.PedidoEntregaDTO

interface PedidoApiService {

    @POST("api/pedidos/entrega/{idUsuario}")
    fun getPedidosByData(
        @Path("idUsuario") idUsuario: Int,
        @Body corpo: PedidoEntregaDTO
    ): Call<PedidoByDataDTO>

    @GET("api/pedidos/datas/{idUsuario}")
    fun getDatasPedidos(@Path("idUsuario") idUsuario: Int): Call<List<DatasPedidosDTO>>

}