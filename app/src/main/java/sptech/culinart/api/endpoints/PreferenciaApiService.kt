package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.GET
import sptech.culinart.api.data.preferencia.PreferenciasDTO

interface PreferenciaApiService {
    @GET("api/preferencias")
    fun getAllPreferencias(): Call<List<PreferenciasDTO>>
}