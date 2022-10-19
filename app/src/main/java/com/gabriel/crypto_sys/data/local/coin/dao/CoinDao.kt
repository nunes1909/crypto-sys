package com.gabriel.crypto_sys.data.local.coin.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.gabriel.crypto_sys.data.local.coin.model.Coin

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salvaCoin(vararg coin: Coin)
}