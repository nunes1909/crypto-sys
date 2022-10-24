package com.gabriel.crypto_sys.data.remote.coin.service

import com.gabriel.crypto_sys.data.remote.coin.model.CoinContainer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoService {
    @GET("{coin}/{method}")
    suspend fun loadlAll(
        @Path(value = "coin", encoded = true)
        cod: String,
        @Path(value = "method", encoded = true)
        type: String? = "ticker"
    ): Response<CoinContainer>
}
