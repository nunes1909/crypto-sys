package com.gabriel.crypto_sys.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabriel.crypto_sys.data.local.carteira.dao.CarteiraDao
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.data.local.coin.dao.CoinDao
import com.gabriel.crypto_sys.data.local.coin.model.Coin
import com.gabriel.crypto_sys.data.local.transacao.dao.TransacaoDao
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao

@Database(
    entities = [
        Coin::class,
        Carteira::class,
        Transacao::class,
    ],
    version = 1,
    exportSchema = true
)
abstract class CryptoSysDataBase : RoomDatabase() {
    abstract fun getCoinDao(): CoinDao
    abstract fun getCarteiraDao(): CarteiraDao
    abstract fun getTransacaoDao(): TransacaoDao
}