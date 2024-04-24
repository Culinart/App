package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import sptech.culinart.api.data.receita.ReceitaDTO

interface ReceitaApiService {

    @GET("api/receitas")
    fun getReceitas(): Call<List<ReceitaDTO>>

    @GET("api/receitas/buscar")
    fun getReceitasPorTermo(@Query("termo") termo: String): Call<List<ReceitaDTO>>
}