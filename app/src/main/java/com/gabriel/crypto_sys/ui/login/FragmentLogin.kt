package com.gabriel.crypto_sys.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gabriel.crypto_sys.databinding.FragmentLoginBinding
import com.gabriel.crypto_sys.data.remote.usuario.model.Usuario
import com.gabriel.crypto_sys.utils.extensions.snackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentLogin : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    private val controller by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goLogar()
        goCadastro()
    }

    private fun goLogar() {
        binding.btnLogarLogin.setOnClickListener {
            limpaCampos()
            getValuesLogin()
        }
    }

    private fun getValuesLogin() = with(binding) {
        val campoEmail = etEmailLogin.text.toString().trim()
        val campoSenha = etSenhaLogin.text.toString().trim()

        if (validaCampos(campoEmail, campoSenha)) {
            autenticaUsuario(campoEmail, campoSenha)
        }
    }

    private fun autenticaUsuario(campoEmail: String, campoSenha: String) {
        viewModel.autentica(
            Usuario(
                email = campoEmail,
                senha = campoSenha
            )
        ).observe(viewLifecycleOwner) { resource ->
            resource?.data?.let { state ->
                if (state) {
                    val action = FragmentLoginDirections.acaoLoginParaCoins()
                    controller.navigate(action)
                } else {
                    view?.snackBar(mensagem = resource.message ?: "Erro durante a autenticação")
                }
            }
        }
    }

    private fun validaCampos(campoEmail: String, campoSenha: String): Boolean {
        var valido = true

        if (campoEmail.isBlank()) {
            binding.etEmailLogin.error = "E-mail é obrigatório"
            valido = false
        }

        if (campoSenha.isBlank()) {
            binding.etSenhaLogin.error = "Senha é obrigatória"
            valido = false
        }

        return valido
    }

    private fun limpaCampos() = with(binding) {
        etEmailLogin.error = null
        etSenhaLogin.error = null
    }

    private fun goCadastro() {
        binding.btnCadastrarLogin.setOnClickListener {
            val action = FragmentLoginDirections.acaoLoginParaCadastro()
            controller.navigate(action)
        }
    }
}
