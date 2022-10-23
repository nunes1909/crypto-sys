package com.gabriel.crypto_sys.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.crypto_sys.databinding.FragmentCadastroUsuarioBinding
import com.gabriel.crypto_sys.data.remote.firebase.model.Usuario
import com.gabriel.crypto_sys.utils.extensions.snackBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroFragment : Fragment() {

    private val binding by lazy { FragmentCadastroUsuarioBinding.inflate(layoutInflater) }
    private val viewModel: CadastroViewModel by viewModel()
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraCadastroUsuario()
    }

    private fun configuraCadastroUsuario() {
        binding.btnCadastrar.setOnClickListener {
            getValuesCadastro()
            limpaErrorCampos()
        }
    }

    private fun getValuesCadastro() = with(binding) {
        val campoEmail = etEmailCadastro.text.toString().trim()
        val campoSenha = etSenhaCadastro.text.toString().trim()
        val campoConfirmaSenha = etSenhaConfirmaCadastro.text.toString().trim()

        if (validaCampos(campoEmail, campoSenha, campoConfirmaSenha)) {
            cadastraUsuario(campoEmail, campoSenha)
        }
    }

    private fun cadastraUsuario(campoEmail: String, campoSenha: String) {
        viewModel.cadastraUsuario(
            Usuario(
                email = campoEmail,
                senha = campoSenha
            )
        ).observe(viewLifecycleOwner) { resource ->
            resource?.data?.let { state ->
                if (state) {
                    view?.snackBar(mensagem = "Cadastro realizado com sucesso")
                    val action = CadastroFragmentDirections.acaoGlobalParaLogin()
                    controller.navigate(action)
                } else {
                    view?.snackBar(mensagem = resource.message ?: "Falha no cadastro")
                }
            }
        }
    }

    private fun validaCampos(email: String, senha: String, confirmaSenha: String): Boolean {
        var valido = true

        if (email.isBlank()) {
            binding.etEmailCadastro.error = "E-mail é necessário"
            valido = false
        }

        if (senha.isBlank()) {
            binding.etSenhaCadastro.error = "Senha é necessária"
            valido = false
        }

        if (senha != confirmaSenha) {
            binding.etSenhaConfirmaCadastro.error = "Senhas diferentes"
            valido = false
        }

        return valido
    }

    private fun limpaErrorCampos() = with(binding) {
        etEmailCadastro.error = null
        etSenhaCadastro.error = null
        etSenhaConfirmaCadastro.error = null
    }
}
