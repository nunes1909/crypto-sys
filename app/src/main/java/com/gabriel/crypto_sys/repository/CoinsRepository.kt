package com.gabriel.crypto_sys.repository

import com.gabriel.crypto_sys.data.remote.service.CryptoService

class CoinsRepository(private val service: CryptoService) {

    suspend fun loadlAll(): List<String> {
        return service.loadlAll()
    }
}