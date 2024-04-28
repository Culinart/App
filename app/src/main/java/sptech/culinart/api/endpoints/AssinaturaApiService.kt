package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.culinart.api.data.assinatura.AssinaturaDTO
import sptech.culinart.api.data.assinatura.PagamentoDTO


interface AssinaturaApiService {

    // Define o método GET para buscar usuários
    @GET("api/assinaturas/{idUsuario}")
    fun getAssinaturaPorIdUsuario(@Path("idUsuario") idUsuario : Int): Call<AssinaturaDTO>

    @POST("api/assinaturas/solicitar/{idUsuario}")
    fun criarAssinatura(@Path("idUsuario") idUsuario: Int) : Call<PagamentoDTO>
}