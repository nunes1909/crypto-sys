package com.gabriel.crypto_sys.ui.carteira

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabriel.crypto_sys.databinding.DialogHistoricoBinding
import com.gabriel.crypto_sys.ui.carteira.adapter.HistoricoAdapter
import com.gabriel.crypto_sys.ui.transacao.TransacaoViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoricoDialog : DialogFragment() {

    private lateinit var binding: DialogHistoricoBinding
    private val args: HistoricoDialogArgs by navArgs()
    private val histAdapter by lazy { HistoricoAdapter() }
    private val transViewModel: TransacaoViewModel by viewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(createView())

        return builder.create()
    }

    private fun createView(): View {
        binding = DialogHistoricoBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            transViewModel.getTransacaoAtual(carteiraId = args.carteiraId)
        }
        configuraRecycler()
        observerTransacoes()
        return binding.root
    }

    private fun configuraRecycler() {
        binding.rvListHistorico.adapter = histAdapter
        binding.rvListHistorico.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observerTransacoes() {
        transViewModel.transacao.observe(this) { transacoes ->
            histAdapter.transacaoList = transacoes!!
        }
    }
}