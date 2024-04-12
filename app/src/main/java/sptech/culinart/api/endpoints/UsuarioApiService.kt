package sptech.culinart.api.endpoints
import retrofit2.Call
import retrofit2.http.GET
interface UsuarioApiService {

    // Define o método GET para buscar usuários
    @GET("/usuarios")
    fun getUsuarios(): Call<List<UsuarioApiService>>
}