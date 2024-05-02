package sptech.culinart.api.endpoints
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import sptech.culinart.api.data.usuario.UsuarioCriacaoDTO
import sptech.culinart.api.data.usuario.UsuarioExibicaoDTO
import sptech.culinart.api.data.usuario.UsuarioLoginDTO
import sptech.culinart.api.data.usuario.UsuarioTokenDTO

interface UsuarioApiService {

    // Define o método GET para buscar usuários
    @GET("api/usuarios")
    fun getUsuarios(): Call<List<UsuarioExibicaoDTO>>

    // Define o método POST para o login de usuário
    @POST("api/usuarios/login")
    fun login(@Body credenciais: UsuarioLoginDTO): Call<UsuarioTokenDTO>

    @POST("api/usuarios/cadastro")
    fun cadastro(@Body usuarioCriacaoDto: UsuarioCriacaoDTO): Call<UsuarioExibicaoDTO>
}