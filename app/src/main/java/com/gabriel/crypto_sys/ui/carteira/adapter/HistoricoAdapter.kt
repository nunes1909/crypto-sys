package com.gabriel.crypto_sys.ui.carteira.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.crypto_sys.data.local.transacao.model.Transacao
import com.gabriel.crypto_sys.databinding.ItemHistoricoBinding

class HistoricoAdapter : RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder>(){

    inner class HistoricoViewHolder(val binding: ItemHistoricoBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differConfig = object : DiffUtil.ItemCallback<Transacao>() {
        override fun areItemsTheSame(oldItem: Transacao, newItem: Transacao): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Transacao, newItem: Transacao): Boolean {
            return  oldItem.id == newItem.id &&
                    oldItem.cod == newItem.cod &&
                    oldItem.quantidade == newItem.quantidade &&
                    oldItem.valor == newItem.valor &&
                    oldItem.carteiraId == newItem.carteiraId
        }
    }

    private val differ = AsyncListDiffer(this, differConfig)

    var transacaoList: List<Transacao>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun getItemCount(): Int = transacaoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
        return HistoricoViewHolder(
            ItemHistoricoBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
        val transacao = transacaoList[position]
        holder.binding.apply {
            tvItemHistoricoCod.text = transacao.cod
            tvItemHistoricoQuantidade.text = transacao.quantidade.toString()
            tvItemHistoricoValor.text = "R$ ${transacao.valor}"
        }
    }
}