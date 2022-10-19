package com.gabriel.crypto_sys.ui.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.gabriel.crypto_sys.databinding.FragmentCoinsBinding
import com.gabriel.crypto_sys.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentCoins : BaseFragment<FragmentCoinsBinding, CoinsViewModel>() {

    override val viewModel: CoinsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAll()
    }

    private fun getAll() = lifecycleScope.launch {
        viewModel.loadALl().observe(viewLifecycleOwner) {

        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCoinsBinding =
        FragmentCoinsBinding.inflate(layoutInflater, container, false)
}
