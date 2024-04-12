package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET

interface ReceitaApiService {

    @GET("/usuarios")
    fun getUsuarios(): Call<List<ReceitaApiService>>
}