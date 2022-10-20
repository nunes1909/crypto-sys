package com.gabriel.crypto_sys.data.local.carteira.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira

@Dao
interface CarteiraDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun salva(carteira: Carteira)

    @Query("SELECT EXISTS (SELECT ID FROM CARTEIRA WHERE ID = :carteiraId)")
    fun verifyIfCarteiraExists(carteiraId: String): Boolean
}