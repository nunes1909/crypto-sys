package com.gabriel.crypto_sys.ui.transacao

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.gabriel.crypto_sys.data.local.carteira.model.Carteira
import com.gabriel.crypto_sys.databinding.DialogNegociarBinding
import com.gabriel.crypto_sys.ui.carteira.CarteiraViewModel
import com.gabriel.crypto_sys.ui.transacao.validaTransacao.ValidaCompra
import com.gabriel.crypto_sys.utils.state.ResourceState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DialogTransacao : DialogFragment() {

    private lateinit var binding: DialogNegociarBinding
    private val args: DialogTransacaoArgs by navArgs()
    private val transViewModel: TransacaoViewModel by viewModel()
    private val cartViewModel: CarteiraViewModel by viewModel()
    private val firebaseAuth: FirebaseAuth by inject()
    private var carteira: Carteira? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(createView())
        lifecycleScope.launch {
            cartViewModel.getCarteiraAtual(firebaseAuth.uid!!)
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
        configuraCompra()
        return binding.root
    }

    private fun configuraComponentes() {
        val preco = "R$ ${args.precoAtual}"
        binding.tvNegociarPrecoAtual.text = preco
    }

    private fun observerCarteiraAtual() {
        cartViewModel.carteira.observe(this) {
            binding.tvNegociarSaldoAtual.text = "R$ ${it!!.saldo}"
            carteira = it
        }
    }

    private fun observerTransacoesAtuais() {
        transViewModel.transacao.observe(this) { transacoes ->
            val investido = transacoes?.sumOf { it.quantidade!! }.toString()
            binding.tvNegociarTotalInvestido.text = "$investido cotas"
        }
    }

    private fun configuraCompra() = with(binding) {
        btnNegociarComprar.setOnClickListener {
            val compra = etQuantidade.text.toString().takeIf { it.trim().isNotEmpty() } ?: "0"

            val resource = ValidaCompra().regraCalculoTransacao(
                precoAtual = args.precoAtual,
                quantidade = compra.toInt(),
                saldo = carteira?.saldo ?: 0,
                codigo = args.codigo
            )

            when (resource) {
                is ResourceState.Success -> {
                    transViewModel.salvaTransacao(
                        resource.data?.apply { this.carteiraId = args.carteiraId }!!
                    )
                    dismiss()
                }
                else -> {
                    etQuantidade.error = resource.message
                }
            }
        }
    }
}
