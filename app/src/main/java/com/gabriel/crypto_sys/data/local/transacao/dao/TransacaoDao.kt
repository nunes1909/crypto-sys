package com.gabriel.crypto_sys.data.local.transacao.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import kotlinx.coroutines.flow.Flow

@Dao
interface TransacaoDao {
    @Insert
    fun salva(transacao: Transacao)

    @Query("SELECT * FROM Transacao WHERE CODIGO = :cod AND ID_CARTEIRA = :carteiraId")
    fun getTransacoes(cod: String?, carteiraId: String): Flow<List<Transacao>>

    @Query("SELECT * FROM Transacao WHERE ID_CARTEIRA = :carteiraId")
    fun getAll(carteiraId: String): Flow<List<Transacao>>
}
