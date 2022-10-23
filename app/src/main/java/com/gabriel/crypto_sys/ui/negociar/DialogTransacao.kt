package com.gabriel.crypto_sys.ui.negociar

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.databinding.DialogNegociarBinding
import com.gabriel.crypto_sys.ui.carteira.CarteiraViewModel
import com.gabriel.crypto_sys.utils.extensions.toast
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogTransacao : DialogFragment() {

    private lateinit var binding: DialogNegociarBinding
    private val args: DialogTransacaoArgs by navArgs()
    private val transViewModel: TransacaoViewModel by viewModel()
    private val cartViewModel: CarteiraViewModel by viewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(createView())
        lifecycleScope.launch {
            cartViewModel.getCarteiraAtual()
        }
        lifecycleScope.launch {
            transViewModel.getTransacaoAtual(args.codigo, args.carteiraId)
        }
        return builder.create()
    }

    private fun createView(): View {
        binding = DialogNegociarBinding.inflate(layoutInflater)
        configuraComponentes()
        observerCarteiraAtual()
        observerTransacoesAtuais()
        configuraTextChange()
        return binding.root
    }

    private fun configuraComponentes() {
        val preco = "R$ ${args.precoAtual}"
        binding.tvNegociarPrecoAtual.text = preco
    }

    private fun observerCarteiraAtual() {
        cartViewModel.carteira.observe(this) {
            binding.tvNegociarSaldoAtual.text = "R$ ${it!!.saldo}"
        }
    }

    private fun observerTransacoesAtuais() {
        transViewModel.transacao.observe(this) { transacoes ->
            val investido = transacoes?.sumOf { it.quantidade!! }.toString()
            binding.tvNegociarTotalInvestido.text = "$investido cotas"
        }
    }

    private fun configuraTextChange() {
        binding.etQuantidade.addTextChangedListener(searchMoviesWatcher())
    }

    private fun searchMoviesWatcher() = object : TextWatcher {
        override fun onTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
            binding.tvNegociarValorTotal.text =
                (args.precoAtual * query.toString().toInt()).toString()
        }

        override fun beforeTextChanged(query: CharSequence, p1: Int, p2: Int, p3: Int) {
            // Sem implementação
        }

        override fun afterTextChanged(p0: Editable?) {
            // Sem implementação
        }
    }
}
