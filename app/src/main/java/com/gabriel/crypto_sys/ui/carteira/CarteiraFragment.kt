package com.gabriel.crypto_sys.ui.carteira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.databinding.FragmentCarteiraBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import com.gabriel.crypto_sys.utils.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarteiraFragment : BaseFragment<FragmentCarteiraBinding, CarteiraViewModel>() {

    override val viewModel: CarteiraViewModel by viewModel()
    private var carteira: Carteira? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaCarteiraAtual()
        inicializaCarteiraGlobal()
        configuraComponentes()
    }

    private fun buscaCarteiraAtual() = lifecycleScope.launch { viewModel.getCarteiraAtual() }

    private fun inicializaCarteiraGlobal() =
        viewModel.carteira.observe(viewLifecycleOwner) { it?.let { carteira = it } }

    private fun configuraComponentes() {
        goDepositar()
        goSacar()
        defineSaldo()
    }

    private fun goDepositar() {
        binding.btnCarteiraDepositar.setOnClickListener {
            goDialogCarteira(carteira!!, true)
        }
    }

    private fun goSacar() {
        binding.btnCarteiraSacar.setOnClickListener {
            carteira?.let { goDialogCarteira(carteira = it, depositar = false) }
        }
    }

    private fun defineSaldo() {
        val saldo = carteira?.saldo?.toString() ?: "0"
        binding.tvCarteiraSaldo.text = "R$ $saldo"
    }

    private fun goDialogCarteira(carteira: Carteira, depositar: Boolean) {
        val action = CarteiraFragmentDirections.acaoCarteiraParaDialogCarteira(
            carteira = carteira,
            depositar = depositar
        )
        controller.navigate(action)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarteiraBinding =
        FragmentCarteiraBinding.inflate(layoutInflater, container, false)
}
