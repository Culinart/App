package sptech.culinart.api.endpoints

import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Path
import sptech.culinart.api.data.assinatura.PagamentoDTO

interface PagamentoApiService {

    @PUT("api/pagamentos/atualizar/status/{idUsuario}")
    fun atualizarStatusPagamento(@Path("idUsuario") idUsuario: Int) : Call<List<PagamentoDTO>>
}