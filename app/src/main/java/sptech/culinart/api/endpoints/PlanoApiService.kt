package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import sptech.culinart.api.data.plano.Plano

interface PlanoApiService {

    @POST("api/planos/{idUsuario}")
    fun cadastrarPlano(
        @Path("idUsuario") idUsuario: Int,
        @Query("cep") cep: String,
        @Query("numero") numero: Int,
        @Query("complemento") complemento: String
    ): Call<Plano>

}