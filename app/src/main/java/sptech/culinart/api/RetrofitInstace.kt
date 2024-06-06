package sptech.culinart.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sptech.culinart.api.endpoints.PedidoApiService
import sptech.culinart.api.endpoints.ReceitaApiService
import sptech.culinart.api.endpoints.AssinaturaApiService
import sptech.culinart.api.endpoints.CategoriaApiService
import sptech.culinart.api.endpoints.EnderecoApiService
import sptech.culinart.api.endpoints.PagamentoApiService
import sptech.culinart.api.endpoints.PlanoApiService
import sptech.culinart.api.endpoints.PreferenciaApiService
import sptech.culinart.api.endpoints.PreferenciaUsuarioApiService
import sptech.culinart.api.endpoints.UsuarioApiService

object RetrofitInstace {
    //local

    val BASE_URL = "http://192.168.15.42:8080/"

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

    fun getReceitasApiService(): ReceitaApiService {
        return retrofit.create(ReceitaApiService::class.java)
    }

    fun getPedidoApiService(): PedidoApiService {
        return retrofit.create(PedidoApiService::class.java)
    }


    // Cria uma instância do serviço Retrofit para end points de pedidos
    fun getPedidosApiService(/*token: String? = null*/): PedidoApiService {
//        val client = createHttpClient(token)
//        val retrofitWithClient = retrofit.newBuilder().client(client).build()
        return retrofit.create(PedidoApiService::class.java)
    }

    fun getAssinaturaApiService(): AssinaturaApiService {
        return retrofit.create(AssinaturaApiService::class.java)
    }

    fun getApiEnderecoService(): EnderecoApiService {
        return retrofit.create(EnderecoApiService::class.java)
    }

    fun getApiCategoriaService(): CategoriaApiService {
        return retrofit.create(CategoriaApiService::class.java)

    }

    fun getPagamentoApiService(): PagamentoApiService {
        return retrofit.create(PagamentoApiService::class.java)
    }

    fun getApiPlanoService(): PlanoApiService {
        return retrofit.create(PlanoApiService::class.java)
    }

    fun getPreferenciaApiService(): PreferenciaApiService {
        return retrofit.create(PreferenciaApiService::class.java)
    }

    fun getPreferenciaUsuarioApiService(): PreferenciaUsuarioApiService {
        return retrofit.create(PreferenciaUsuarioApiService::class.java)
    }

}