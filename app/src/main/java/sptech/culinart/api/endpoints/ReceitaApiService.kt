package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import sptech.culinart.api.data.receita.ReceitaDTO
import sptech.culinart.api.data.receita.ReceitaExibicaoDTO

interface ReceitaApiService {

    @GET("api/receitas")
    fun getReceitas(): Call<List<ReceitaDTO>>

    @GET("api/receitas/buscar")
    fun getReceitasPorTermo(@Query("termo") termo: String): Call<List<ReceitaDTO>>

    @GET("api/receitas/{id}")
    fun getOneReceita(@Path("id") idReceita: Int): Call<ReceitaExibicaoDTO>
}