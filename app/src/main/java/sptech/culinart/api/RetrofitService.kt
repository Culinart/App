package sptech.culinart.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    const val BASE_URL = "https://653dc13df52310ee6a9a4ab7.mockapi.io/preferencias/"

    fun getMockPreferenciasService(): MockPreferencia {
        val preferencias =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MockPreferencia::class.java)

        return preferencias
    }
}