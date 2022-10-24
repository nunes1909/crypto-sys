package com.gabriel.crypto_sys.data.remote.coin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CoinContainer(
    @SerializedName("ticker")
    val response: CoinResponse
) : Serializable
