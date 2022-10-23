package com.gabriel.crypto_sys.ui.transacao.validaTransacao

import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import com.gabriel.crypto_sys.utils.state.ResourceState

class ValidaCompra : ValidaTransacao {
    override fun regraCalculoTransacao(
        precoAtual: Int,
        quantidade: Int,
        saldo: Int,
        codigo: String
    ): ResourceState<Transacao> {
        if (precoAtual <= 0) {
            return ResourceState.Error(message = "O preço atual é zero.")
        }
        if (saldo <= 0 || quantidade * precoAtual > saldo) {
            return ResourceState.Error(message = "Saldo insuficiente.")
        }
        if (quantidade <= 0) {
            return ResourceState.Error(message = "Quantidade insuficiente.")
        }
        return ResourceState.Success(
            data = Transacao(
                id = 0L,
                cod = codigo,
                quantidade = quantidade,
                valor = quantidade * precoAtual
            )
        )
    }
}
