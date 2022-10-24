package com.gabriel.crypto_sys.ui.coins.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabriel.crypto_sys.data.local.coin.model.Coin
import com.gabriel.crypto_sys.databinding.ItemCoinBinding
import com.gabriel.crypto_sys.utils.extensions.limitValue

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    inner class CoinViewHolder(val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differConfig = object : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return  oldItem.id == newItem.id &&
                    oldItem.cod == newItem.cod &&
                    oldItem.title == newItem.title
        }
    }

    private val differ = AsyncListDiffer(this, differConfig)

    var coinsList: List<Coin>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun getItemCount(): Int = coinsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = coinsList[position]
        holder.binding.apply {
            tvItemCoinCod.text = coin.cod?.limitValue(5, true) ?: ""
            tvItemCoinTitle.text = coin.title?.limitValue(25, true) ?: "Coin sem título"
        }

        holder.itemView.setOnClickListener {
            onCoinClickListener?.let {
                it(coin)
            }
        }
    }

    fun setCoinOnClickListener(onClick: (Coin) -> Unit) {
        onCoinClickListener = onClick
    }

    private var onCoinClickListener: ((Coin) -> Unit)? = null
}
