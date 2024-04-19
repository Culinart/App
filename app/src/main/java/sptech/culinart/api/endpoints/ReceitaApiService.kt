package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import sptech.culinart.api.data.receita.ReceitasDTO

interface ReceitaApiService {

    @GET("api/receitas")
    fun getReceitas(): Call<List<ReceitasDTO>>
}