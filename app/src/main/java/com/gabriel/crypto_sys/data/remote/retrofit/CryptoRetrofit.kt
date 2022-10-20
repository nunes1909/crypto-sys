package com.gabriel.crypto_sys.data.remote.retrofit

import com.gabriel.crypto_sys.data.remote.coin.service.CryptoService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CryptoRetrofit {

    fun getRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.mercadobitcoin.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getCryptoService(retrofit: Retrofit): CryptoService {
        return retrofit.create(CryptoService::class.java)
    }
}