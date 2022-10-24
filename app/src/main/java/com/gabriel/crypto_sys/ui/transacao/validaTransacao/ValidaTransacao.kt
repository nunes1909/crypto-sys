package com.gabriel.crypto_sys.ui.transacao.validaTransacao

import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import com.gabriel.crypto_sys.utils.state.ResourceState

interface ValidaTransacao {
    fun regraCalculoTransacao(
        precoAtual: Int,
        quantidade: Int,
        saldo: Int,
        codigo: String
    ): ResourceState<Transacao>
}
