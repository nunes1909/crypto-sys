package com.gabriel.crypto_sys.ui.coins

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.crypto_sys.databinding.FragmentCoinsBinding
import com.gabriel.crypto_sys.ui.base.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinsFragment : BaseFragment<FragmentCoinsBinding, CoinsViewModel>() {

    override val viewModel: CoinsViewModel by viewModel()
    private val coinAdapter by lazy { CoinAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyler()
        configuraPesquisa()
        loadAll()
        configuraClickAdapter()
        observer()
    }

    private fun configuraRecyler() {
        binding.rvListCoins.adapter = coinAdapter
        binding.rvListCoins.layoutManager = LinearLayoutManager(requireContext())
        binding.rvListCoins.requestFocus()
    }

    private fun configuraPesquisa() {
        binding.etPesquisaCoins.addTextChangedListener(searchCoinsWatcher())
    }

    private fun searchCoinsWatcher() = object : TextWatcher {
        override fun onTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
            if (query.isNotEmpty()) {
                viewModel.loadALl(query = query.toString().uppercase())
            } else {
                viewModel.loadALl()
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            // Sem implementação
        }

        override fun afterTextChanged(p0: Editable?) {
            // Sem implementação
        }
    }

    private fun loadAll(query: String? = null) {
        viewModel.loadALl(query = query)
    }

    private fun observer() {
        viewModel.getFav.observe(viewLifecycleOwner) { result ->
            coinAdapter.coinsList = result
        }
    }

    private fun configuraClickAdapter() {
        coinAdapter.setCoinOnClickListener { coin ->
            val action = CoinsFragmentDirections.acaoCoinsParaDetalhes(coin = coin)
            controller.navigate(action)
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCoinsBinding =
        FragmentCoinsBinding.inflate(layoutInflater, container, false)
}
