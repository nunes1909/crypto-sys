package com.gabriel.crypto_sys.repository.coin

import com.gabriel.crypto_sys.data.local.coin.dao.CoinDao
import com.gabriel.crypto_sys.data.local.coin.model.Coin
import com.gabriel.crypto_sys.data.remote.coin.model.CoinResponse
import com.gabriel.crypto_sys.data.remote.coin.service.CryptoService
import com.gabriel.crypto_sys.utils.state.ResourceState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import java.lang.Exception

class CoinsRepository(private val dao: CoinDao) {

    fun loadlAll(query: String? = null): Flow<List<Coin>> {
        return if (!query.isNullOrEmpty()) {
            val queryLike = "%$query%"
            dao.searchCoin(query = queryLike)
        } else {
            dao.getAll()
        }
    }
}
