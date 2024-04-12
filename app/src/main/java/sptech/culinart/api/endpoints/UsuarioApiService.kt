package sptech.culinart.api.endpoints
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioApiService {

    // Define o método GET para buscar usuários
    @GET("/usuarios")
    fun getUsuarios(): Call<List<UsuarioApiService>>

    // Define o método POST para o login de usuário
    @POST("/login")
    fun login(@Body credenciais: CredenciaisLogin): Call<UsuarioTokenDto>
}