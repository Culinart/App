package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import sptech.culinart.model.Endereco

interface EnderecoApiService {

    @POST("api/enderecos/{idUsuario}")
    fun createEnderecoUsuario(
        @Path("idUsuario") idUsuario: Int,
        @Query("cep") cep: String,
        @Query("numero") numero: Int,
        @Query("complemento") complemento: String
    ): Call<Endereco>

    @GET("api/enderecos/buscarCEP")
    fun getCep(@Query("cep") cep: String): Call<List<Endereco>>

}