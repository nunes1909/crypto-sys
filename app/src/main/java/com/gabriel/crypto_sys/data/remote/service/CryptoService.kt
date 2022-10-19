package com.gabriel.crypto_sys.data.remote.service

import retrofit2.http.GET

interface CryptoService {
    @GET("coins")
    suspend fun loadlAll(): List<String>
}