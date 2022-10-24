package com.gabriel.crypto_sys.ui.detalhes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import com.gabriel.crypto_sys.data.remote.coin.model.CoinResponse
import com.gabriel.crypto_sys.databinding.FragmentDetalhesBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import com.gabriel.crypto_sys.utils.extensions.toast
import com.gabriel.crypto_sys.utils.state.ResourceState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt

class DetalhesFragment : BaseFragment<FragmentDetalhesBinding, DetalhesViewModel>() {

    override val viewModel: DetalhesViewModel by viewModel()
    private val args: DetalhesFragmentArgs by navArgs()
    private var precoAtual: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            getDetalhes(args.coin.cod!!)
        }
        observerDetalhes()
        configuraClickNegociar()
    }

    private suspend fun getDetalhes(cod: String) = viewModel.getDetalhes(cod = cod)

    private fun observerDetalhes() {
        viewModel.detalhes.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is ResourceState.Success -> {
                    preencheDetalhes(resource)
                }
                is ResourceState.Error -> {
                    toast(resource.message!!)
                }
                else -> {}
            }
        }
    }

    private fun configuraClickNegociar() {
        binding.btnDetalhesNegociar.setOnClickListener {
            val action = DetalhesFragmentDirections
                .actionFragmentDetalhesToDialogNegociar(
                    precoAtual = precoAtual.toInt(),
                    codigo = args.coin.cod!!,
                    carteiraId = getUserAtual()?.uid!!
                )
            controller.navigate(action)
        }
    }

    private fun preencheDetalhes(resource: ResourceState<CoinResponse>) = with(binding) {
        resource.data?.let { coinResponse ->
            tvDetalhesCoinTitle.text = args.coin.cod

            val menorPreco = coinResponse.menorPreco.roundToInt() / 100.00
            tvDetalhesMenorPreco.text = "R$ $menorPreco"

            val maiorPreco = coinResponse.maiorPreco.roundToInt() / 100.00
            tvDetalhesMaiorPreco.text = "R$ $maiorPreco"

            precoAtual = coinResponse.precoAtual.roundToInt() / 100.00
            tvDetalhesPrecoAtual.text = "R$ $precoAtual"
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetalhesBinding =
        FragmentDetalhesBinding.inflate(layoutInflater, container, false)
}
