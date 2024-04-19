package sptech.culinart.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import sptech.culinart.model.LoginRequest
import sptech.culinart.model.LoginResponse
import sptech.culinart.model.Usuario

interface ApiUsuario {

    @POST("/api/usuarios/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

}