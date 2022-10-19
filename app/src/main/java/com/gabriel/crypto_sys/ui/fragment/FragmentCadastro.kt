package com.gabriel.crypto_sys.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.crypto_sys.databinding.FragmentCadastroUsuarioBinding
import com.gabriel.crypto_sys.model.Usuario
import com.gabriel.crypto_sys.ui.base.BaseFragment
import com.gabriel.crypto_sys.ui.extensions.snackBar
import com.gabriel.crypto_sys.ui.viewModel.CadastroViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCadastro : BaseFragment<FragmentCadastroUsuarioBinding, CadastroViewModel>() {

    override val viewModel: CadastroViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraCadastroUsuario()
    }

    private fun configuraCadastroUsuario() {
        binding.btnCadastrar.setOnClickListener {
            limpaErrorCampos()
            getValuesCadastro()
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
                    val action = FragmentCadastroDirections.acaoGlobalParaLogin()
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

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCadastroUsuarioBinding =
        FragmentCadastroUsuarioBinding.inflate(layoutInflater, container, false)
}
