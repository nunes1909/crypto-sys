package com.gabriel.crypto_sys.ui.negociar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import com.gabriel.crypto_sys.repository.transacao.TransacaoRepository
import kotlinx.coroutines.flow.collect

class TransacaoViewModel(private val repository: TransacaoRepository) : ViewModel() {

    private val _transacao = MutableLiveData<List<Transacao>?>()
    val transacao = _transacao as LiveData<List<Transacao>?>

    suspend fun getTransacaoAtual(cod: String, carteiraId: String) {
        repository.getTransacoes(cod, carteiraId).collect { transacoes ->
            _transacao.value = transacoes
        }
    }
}