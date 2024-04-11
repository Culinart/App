package sptech.culinart.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.culinart.model.Endereco

interface ApiEndereco {

    @POST("/enderecos/{idUsuario}")
    fun createEndereco(@Path("idUsuario") idUsuario: Int): Call<Endereco>

}