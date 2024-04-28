package sptech.culinart.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sptech.culinart.api.endpoints.PedidoApiService
import sptech.culinart.api.endpoints.UsuarioApiService

object RetrofitInstace {
    //local
    val BASE_URL = "http://192.168.15.153:8080/"
    //internet
    //private const val BASE_URL = "https://sua-api.com/"


    // Cria uma instância Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Cria uma instância do serviço Retrofit para end points de usuarios
    fun getUsuarioApiService(): UsuarioApiService {
        return retrofit.create(UsuarioApiService::class.java)
    }

    fun getReceitaApiService(): UsuarioApiService {
        return retrofit.create(UsuarioApiService::class.java)
    }

    // Cria uma instância do serviço Retrofit para end points de pedidos
    fun getPedidosApiService(/*token: String? = null*/): PedidoApiService {
//        val client = createHttpClient(token)
//        val retrofitWithClient = retrofit.newBuilder().client(client).build()
        return retrofit.create(PedidoApiService::class.java)
    }

}