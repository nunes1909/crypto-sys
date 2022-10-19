package com.gabriel.crypto_sys.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.model.Usuario
import com.gabriel.crypto_sys.repository.FirebaseRepository
import com.gabriel.crypto_sys.ui.state.ResourceState

class LoginViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun autentica(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        return repository.autenticaUsuario(usuario = usuario)
    }
}
