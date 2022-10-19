package com.gabriel.crypto_sys.ui.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.repository.CoinsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CoinsViewModel(private val repository: CoinsRepository) : ViewModel() {

    suspend fun loadALl(): LiveData<List<String>> {
        val liveData = MutableLiveData<List<String>>()
        CoroutineScope(IO).launch {
            liveData.postValue(repository.loadlAll())
        }
        return liveData
    }
}
