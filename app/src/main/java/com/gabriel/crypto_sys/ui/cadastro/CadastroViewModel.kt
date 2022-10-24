package com.gabriel.crypto_sys.ui.cadastro

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gabriel.crypto_sys.data.remote.firebase.model.Usuario
import com.gabriel.crypto_sys.repository.firebase.FirebaseRepository
import com.gabriel.crypto_sys.utils.state.ResourceState

class CadastroViewModel(private val repository: FirebaseRepository) : ViewModel() {

    fun cadastraUsuario(usuario: Usuario): LiveData<ResourceState<Boolean>> {
        return repository.cadastraUsuario(usuario = usuario)
    }
}
