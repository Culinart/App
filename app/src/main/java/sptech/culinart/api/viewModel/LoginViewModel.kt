package sptech.culinart.api.viewModel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.culinart.CadastroEndereco
import sptech.culinart.Pedido
import sptech.culinart.api.RetrofitInstace
import sptech.culinart.api.data.PreferencesManager
import sptech.culinart.api.data.usuario.UsuarioLoginDTO
import sptech.culinart.api.data.usuario.UsuarioTokenDTO

class LoginViewModel : ViewModel(){
    val api = RetrofitInstace.getUsuarioApiService()

fun login(credenciais: UsuarioLoginDTO,contexto: Context){
    api.login(credenciais).enqueue(object : Callback<UsuarioTokenDTO> {
        override fun onResponse(call: Call<UsuarioTokenDTO>, response: Response<UsuarioTokenDTO>) {
            if (response.isSuccessful) {
                val usuarioTokenDTO = response.body()
                println(response.body())
                val prefsManager = PreferencesManager.getInstance(contexto)
                if (usuarioTokenDTO != null) {
                    usuarioTokenDTO.token.let { prefsManager.saveToken(it) }
                    usuarioTokenDTO.nome.let { prefsManager.saveName(it) }
                    usuarioTokenDTO.permissao.let { prefsManager.savePermission(it) }
                    usuarioTokenDTO.userID.let { prefsManager.saveUserId(it) }
                    usuarioTokenDTO.email.let { prefsManager.saveEmail(it) }
                    usuarioTokenDTO.isAtivo.let { prefsManager.saveIsAtivo(it) }

                    if (usuarioTokenDTO.permissao == "CLIENTE") {
                        val pedido = Intent(contexto, Pedido::class.java)
                        pedido.putExtra("token", usuarioTokenDTO.token)
                        pedido.putExtra("nome", usuarioTokenDTO.nome)
                        pedido.putExtra("permissao", usuarioTokenDTO.permissao)
                        pedido.putExtra("userID", usuarioTokenDTO.userID)
                        pedido.putExtra("email", usuarioTokenDTO.email)
                        pedido.putExtra("isAtivo", usuarioTokenDTO.isAtivo)
                        contexto.startActivity(pedido)
                    } else {
                        val cadastroEndereco = Intent(contexto, CadastroEndereco::class.java)
                        cadastroEndereco.putExtra("token", usuarioTokenDTO.token)
                        cadastroEndereco.putExtra("nome", usuarioTokenDTO.nome)
                        cadastroEndereco.putExtra("permissao", usuarioTokenDTO.permissao)
                        cadastroEndereco.putExtra("userID", usuarioTokenDTO.userID)
                        cadastroEndereco.putExtra("email", usuarioTokenDTO.email)
                        cadastroEndereco.putExtra("isAtivo", usuarioTokenDTO.isAtivo)
                        contexto.startActivity(cadastroEndereco)
                    }


                }
                println("Token salvo: ${prefsManager.getToken()}")
                println("Nome salvo: ${prefsManager.getName()}")

            } else {
                println("Deu ruim")
            }
        }

        override fun onFailure(call: Call<UsuarioTokenDTO>, t: Throwable) {
            println(t)
        }
    })
}

}