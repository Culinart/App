package sptech.culinart.api
import retrofit2.Call
import retrofit2.http.GET
import sptech.culinart.classes.Preferencia

interface MockPreferencia {
    @GET("preferencias")
    fun get(): Call<List<Preferencia>>
}