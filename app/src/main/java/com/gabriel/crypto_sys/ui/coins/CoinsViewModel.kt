package com.gabriel.crypto_sys.ui.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabriel.crypto_sys.data.local.coin.model.Coin
import com.gabriel.crypto_sys.repository.coin.CoinsRepository
import kotlinx.coroutines.launch

class CoinsViewModel(private val repository: CoinsRepository) : ViewModel() {

    private val _getFav = MutableLiveData<List<Coin>>()
    val getFav: LiveData<List<Coin>> = _getFav

    fun loadALl(query: String? = null) = viewModelScope.launch {
        val flowCoins = repository.loadlAll(query = query)
        flowCoins.collect { results ->
            _getFav.value = results
        }
    }
}
