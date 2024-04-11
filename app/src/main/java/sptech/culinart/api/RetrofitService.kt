package sptech.culinart.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    const val BASE_URL = "https://localhost:8080"

    fun getApiUsuarioService(): ApiUsuario{
        val client =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiUsuario::class.java)

        return client
    }

    fun getApiEnderecoService(): ApiEndereco{
        val client =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiEndereco::class.java)

        return client
    }

    fun getApiPlanoService(): ApiPlano{
        val client =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiPlano::class.java)

        return client
    }

    fun getApiAssinaturaService(): ApiAssinatura{
        val client =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiAssinatura::class.java)

        return client
    }

}