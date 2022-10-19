package com.gabriel.crypto_sys.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabriel.crypto_sys.data.local.coin.dao.CoinDao
import com.gabriel.crypto_sys.data.local.coin.model.Coin

@Database(
    entities = [
        Coin::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CryptoSysDataBase : RoomDatabase() {
    abstract fun getCoinDao(): CoinDao
}