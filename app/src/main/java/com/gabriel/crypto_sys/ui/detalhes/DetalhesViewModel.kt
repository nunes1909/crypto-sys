package com.gabriel.crypto_sys.ui.detalhes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.remote.coin.model.CoinResponse
import com.gabriel.crypto_sys.repository.datalhes.DetalhesRepository
import com.gabriel.crypto_sys.utils.state.ResourceState

class DetalhesViewModel(private val repository: DetalhesRepository) : ViewModel() {

    private val _detalhes = MutableLiveData<ResourceState<CoinResponse>>()
    val detalhes: LiveData<ResourceState<CoinResponse>> = _detalhes

    suspend fun getDetalhes(cod: String) {
        _detalhes.value = repository.loadTicker(cod = cod)
    }
}
