package com.gabriel.crypto_sys.data.remote.coin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CoinResponse(
    @SerializedName("high")
    val maiorPreco: Double,
    @SerializedName("low")
    val menorPreco: Double,
    @SerializedName("last")
    val precoAtual: Double,
) : Serializable
