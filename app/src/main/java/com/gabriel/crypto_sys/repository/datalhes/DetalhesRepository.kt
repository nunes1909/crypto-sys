package com.gabriel.crypto_sys.repository.datalhes

import com.gabriel.crypto_sys.data.remote.coin.model.CoinContainer
import com.gabriel.crypto_sys.data.remote.coin.model.CoinResponse
import com.gabriel.crypto_sys.data.remote.coin.service.CryptoService
import com.gabriel.crypto_sys.utils.state.ResourceState
import retrofit2.Response
import java.lang.Exception

class DetalhesRepository(private val service: CryptoService) {

    suspend fun loadTicker(cod: String): ResourceState<CoinResponse> {
        return try {
            val response = service.loadlAll(cod = cod)
            validaResponse(response)
        } catch (e: Exception) {
            ResourceState.Error(message = e.message)
        }
    }

    private fun validaResponse(response: Response<CoinContainer>): ResourceState<CoinResponse> {
        if (response.isSuccessful) {
            response.body()?.let { coinResponse ->
                return ResourceState.Success(data = coinResponse.response)
            }
        }
        return ResourceState.Error(cod = response.code(), message = response.message())
    }
}
