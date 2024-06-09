package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.culinart.api.data.usuario.UsuarioPreferenciaDTO

interface PreferenciaUsuarioApiService {
    @GET("/api/usuarios/preferencias/{idUsuario}")
    fun getPreferenciasUsuario(@Path("idUsuario") idUsuario: Int): Call<List<UsuarioPreferenciaDTO>>


    @DELETE("/api/usuarios/preferencias/{idUsuario}")
    fun deletePreferenciasUsuario(@Path("idUsuario") idUsuario: Int): Call<Void>

    @POST("/api/usuarios/preferencias/{idPreferencia}/{idUsuario}")
    fun postPreferenciasUsuario(@Path("idPreferencia") idPreferencia: Int, @Path("idUsuario") idUsuario: Int): Call<Void>

}