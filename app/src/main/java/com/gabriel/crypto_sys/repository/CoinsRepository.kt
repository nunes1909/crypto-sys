package com.gabriel.crypto_sys.repository

import com.gabriel.crypto_sys.data.local.coin.dao.CoinDao
import com.gabriel.crypto_sys.data.local.coin.model.Coin
import kotlinx.coroutines.flow.Flow

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