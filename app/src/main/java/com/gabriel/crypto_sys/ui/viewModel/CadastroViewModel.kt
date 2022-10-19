package com.gabriel.crypto_sys.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.model.Usuario
import com.gabriel.crypto_sys.repository.FirebaseRepository
import com.gabriel.crypto_sys.ui.state.ResourceState

class CadastroViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun cadastraUsuario(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        return repository.cadastraUsuario(usuario = usuario)
    }
}
