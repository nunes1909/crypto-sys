package com.gabriel.crypto_sys.data.local.coin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabriel.crypto_sys.data.local.coin.model.Coin
import kotlinx.coroutines.flow.Flow

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvaCoin(vararg coin: Coin)

    @Query("DELETE FROM COIN")
    fun deletaCoin()

    @Query("SELECT * FROM COIN ORDER BY CODIGO")
    fun getAll(): Flow<List<Coin>>

    @Query("SELECT * FROM COIN WHERE CODIGO LIKE :query")
    fun searchCoin(query: String): Flow<List<Coin>>
}