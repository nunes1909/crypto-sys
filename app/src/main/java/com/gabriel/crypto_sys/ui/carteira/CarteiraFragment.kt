package com.gabriel.crypto_sys.ui.carteira

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.databinding.FragmentCarteiraBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarteiraFragment : BaseFragment<FragmentCarteiraBinding, CarteiraViewModel>() {

    override val viewModel: CarteiraViewModel by viewModel()
    private lateinit var carteiraGlobal: Carteira

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerCarteira()
    }

    private fun observerCarteira() {
        viewModel.carteira.observe(viewLifecycleOwner) { carteira ->
            if (carteira != null) {
                carteiraGlobal = carteira
                defineSaldo()
                goDepositar()
                goSacar()
                goHistorico()
            }
        }
    }

    private fun goDepositar() {
        binding.btnCarteiraDepositar.setOnClickListener {
            goDialogCarteira(depositar = true, carteira = carteiraGlobal)
        }
    }

    private fun goSacar() {
        binding.btnCarteiraSacar.setOnClickListener {
            goDialogCarteira(depositar = false, carteira = carteiraGlobal)
        }
    }

    private fun goHistorico() {
        binding.btnCarteiraHistorico.setOnClickListener {
            val action = CarteiraFragmentDirections.acaoCarteiraParaHistorico(carteiraGlobal.id)
            controller.navigate(action)
        }
    }

    private fun defineSaldo() {
        binding.tvCarteiraSaldo.text = "R$ ${carteiraGlobal.saldo}"
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
