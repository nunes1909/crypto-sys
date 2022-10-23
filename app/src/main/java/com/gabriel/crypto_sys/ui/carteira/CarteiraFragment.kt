package com.gabriel.crypto_sys.ui.carteira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.databinding.FragmentCarteiraBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarteiraFragment : BaseFragment<FragmentCarteiraBinding, CarteiraViewModel>() {

    override val viewModel: CarteiraViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buscaCarteiraAtual()
        observerCarteira()
    }

    private fun buscaCarteiraAtual() = lifecycleScope.launch { viewModel.getCarteiraAtual() }

    private fun observerCarteira() {
        viewModel.carteira.observe(viewLifecycleOwner) { carteira ->
            if (carteira != null) {
                configuraComponentes(carteira)
            }
        }
    }

    private fun configuraComponentes(carteira: Carteira) {
        defineSaldo(carteira)
        goDepositar(carteira)
        goSacar(carteira)
    }

    private fun goDepositar(carteira: Carteira) {
        binding.btnCarteiraDepositar.setOnClickListener {
            goDialogCarteira(depositar = true, carteira = carteira)
        }
    }

    private fun goSacar(carteira: Carteira) {
        binding.btnCarteiraSacar.setOnClickListener {
            goDialogCarteira(depositar = false, carteira = carteira)
        }
    }

    private fun defineSaldo(carteira: Carteira) {
        binding.tvCarteiraSaldo.text = "R$ ${carteira.saldo}"
    }

    private fun goDialogCarteira(depositar: Boolean, carteira: Carteira) {
        val action = CarteiraFragmentDirections.acaoCarteiraParaDialogCarteira(
            depositar = depositar,
            carteira = carteira
        )
        controller.navigate(action)
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarteiraBinding =
        FragmentCarteiraBinding.inflate(layoutInflater, container, false)
}
