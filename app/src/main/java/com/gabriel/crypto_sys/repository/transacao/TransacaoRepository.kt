package com.gabriel.crypto_sys.repository.transacao

import com.gabriel.crypto_sys.data.local.transacao.dao.TransacaoDao
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import kotlinx.coroutines.flow.Flow

class TransacaoRepository(private val dao: TransacaoDao) {

    fun salvaTransacao(transacao: Transacao) {
        dao.salva(transacao = transacao)
    }

    fun getTransacoes(cod: String, carteiraId: String): Flow<List<Transacao>> {
        return dao.getTransacoes(cod, carteiraId)
    }
}
