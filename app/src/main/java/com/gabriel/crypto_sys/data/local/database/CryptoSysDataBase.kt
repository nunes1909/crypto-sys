package com.gabriel.crypto_sys.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabriel.crypto_sys.data.local.carteira.dao.CarteiraDao
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.data.local.coin.dao.CoinDao
import com.gabriel.crypto_sys.data.local.coin.model.Coin

@Database(
    entities = [
        Coin::class,
        Carteira::class
    ],
    version = 1,
    exportSchema = true
)
abstract class CryptoSysDataBase : RoomDatabase() {
    abstract fun getCoinDao(): CoinDao
    abstract fun getCarteiraDao(): CarteiraDao
}