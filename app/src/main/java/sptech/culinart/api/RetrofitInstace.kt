package sptech.culinart.api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import sptech.culinart.api.endpoints.PedidoApiService
import sptech.culinart.api.endpoints.UsuarioApiService

object RetrofitInstace {
    // Base URL
    private const val BASE_URL = "http://192.168.15.42:8080/"

    // Configuração do logging interceptor para logar os cabeçalhos das requisições
    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        println(message) // Aqui você pode fazer o log dos cabeçalhos como desejar
    }.apply {
        level = HttpLoggingInterceptor.Level.HEADERS // Nível de log para incluir apenas os cabeçalhos
    }

    // Configuração do cliente HTTP do Retrofit com o interceptor
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Cria uma instância Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    // Cria um cliente HTTP com o interceptor de token
    private fun createHttpClient(token: String?): OkHttpClient {
        return if (!token.isNullOrEmpty()) {
            httpClient.newBuilder().addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }.build()
        } else {
            httpClient
        }
    }

    // Cria uma instância do serviço Retrofit para end points de usuarios
    fun getUsuarioApiService(token: String? = null): UsuarioApiService {
        val client = createHttpClient(token)
        val retrofitWithClient = retrofit.newBuilder().client(client).build()
        return retrofitWithClient.create(UsuarioApiService::class.java)
    }

    // Cria uma instância do serviço Retrofit para end points de pedidos
    fun getPedidosApiService(token: String? = null): PedidoApiService {
        val client = createHttpClient(token)
        val retrofitWithClient = retrofit.newBuilder().client(client).build()
        return retrofitWithClient.create(PedidoApiService::class.java)
    }

}