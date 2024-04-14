package sptech.culinart.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import sptech.culinart.model.Endereco

interface ApiEndereco {

    @POST("/enderecos/{idUsuario}")
    fun createEnderecoUsuario(@Path("idUsuario") idUsuario: Int, @Body endereco: Endereco): Call<Endereco>

    @GET("/enderecos/buscarCEP")
    fun getCep(@Query("cep") cep: String): Call<List<Endereco>>

}