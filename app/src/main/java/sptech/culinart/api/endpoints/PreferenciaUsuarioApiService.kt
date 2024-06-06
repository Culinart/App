package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import sptech.culinart.api.data.usuario.UsuarioPreferenciaDTO

interface PreferenciaUsuarioApiService {
    @GET("/api/usuarios/preferencias/{idUsuario}")
    fun getPreferenciasUsuario(@Path("idUsuario") idUsuario: Int): Call<List<UsuarioPreferenciaDTO>>

}