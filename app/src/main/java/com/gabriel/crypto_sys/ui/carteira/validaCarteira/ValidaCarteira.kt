package com.gabriel.crypto_sys.ui.carteira.validaCarteira

import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.utils.state.ResourceState

interface ValidaCarteira {
    fun regraCalculo(valor: Int?, carteira: Carteira): ResourceState<Carteira>
}
