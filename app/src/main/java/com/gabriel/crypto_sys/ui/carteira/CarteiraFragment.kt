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
//        goSacar(carteira)
    }

    private fun goDepositar(carteira: Carteira) {
        binding.btnCarteiraDepositar.setOnClickListener {
            val action = CarteiraFragmentDirections.acaoCarteiraParaDialogCarteira(
                depositar = true,
                carteira = carteira
            )
            controller.navigate(action)
        }
    }
//
//    private fun goSacar(carteira: Carteira) {
//        binding.btnCarteiraSacar.setOnClickListener {
//            goDialogCarteira(carteira, depositar = false)
//        }
//    }

    private fun defineSaldo(carteira: Carteira) {
        binding.tvCarteiraSaldo.text = "R$ ${carteira.saldo}"
    }

//    private fun goDialogCarteira(carteira: Carteira, depositar: Boolean) {
//        val action = CarteiraFragmentDirections.acaoCarteiraParaDialogCarteira(
//            id = carteira.id,
//            saldo = carteira.saldo ?: "0",
//            depositar = depositar
//        )
//        controller.navigate(action)
//    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCarteiraBinding =
        FragmentCarteiraBinding.inflate(layoutInflater, container, false)
}
