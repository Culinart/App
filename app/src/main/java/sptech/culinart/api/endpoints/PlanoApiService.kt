package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.culinart.api.data.plano.PlanoRequestDTO
import sptech.culinart.api.data.plano.PlanoResponseDTO
import sptech.culinart.api.data.plano.planoCategoria.PlanoCategoriaRequest
import sptech.culinart.api.data.plano.planoCategoria.PlanoCategoriaResponse

interface PlanoApiService {

    @POST("api/planos/{idUsuario}")
    fun cadastrarPlano(
        @Path("idUsuario") idUsuario: Int,
        @Body planoRequestDTO:PlanoRequestDTO
    ): Call<PlanoResponseDTO>

    @POST("api/planos/categorias")
    fun cadastrarPlanoCategoria(
        @Body planoCategoriaCadastro:PlanoCategoriaRequest
    ): Call<PlanoCategoriaResponse>

}