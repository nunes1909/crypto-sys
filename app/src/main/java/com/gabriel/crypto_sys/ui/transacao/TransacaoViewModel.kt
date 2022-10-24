package com.gabriel.crypto_sys.ui.transacao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import com.gabriel.crypto_sys.repository.carteira.CarteiraRepository
import com.gabriel.crypto_sys.repository.transacao.TransacaoRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TransacaoViewModel(
    private val transacaoRepository: TransacaoRepository,
    private val carteiraRepository: CarteiraRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _transacao = MutableLiveData<List<Transacao>>()
    val transacao = _transacao as LiveData<List<Transacao>>

    suspend fun getTransacaoAtual(cod: String = "", carteiraId: String) {
        transacaoRepository.getTransacoes(cod, carteiraId).collect { transacoes ->
            _transacao.value = transacoes
        }
    }

    fun salvaTransacao(transacao: Transacao) = CoroutineScope(IO).launch {
        transacaoRepository.salvaTransacao(transacao = transacao).also {
            descontaNoSaldoAtual(transacao.valor!!)
        }
    }

    private suspend fun descontaNoSaldoAtual(valor: Int) {
        val carteiraAtual = carteiraRepository.getCarteiraAtual(firebaseAuth.uid!!).first()
        carteiraRepository.salvaCarteira( carteiraAtual.apply { this!!.saldo -= valor } )
    }
}