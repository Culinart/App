package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import sptech.culinart.api.data.categoria.Categoria

interface CategoriaApiService {

    @GET("/categorias")
    fun getCategorias(): Call<List<Categoria>>

}