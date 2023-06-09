package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglist.databinding.ItemShopDisabledBinding
import com.example.shoppinglist.databinding.ItemShopEnabledBinding
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter: ListAdapter<ShopItem,ShopItemViewHolder>(ShopItemDiffCallback()) {
    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemClickListener : ((ShopItem)->Unit)? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return if (viewType == VIEW_TYPE_ENABLED){
            val binding = ItemShopEnabledBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ShopItemViewHolder(binding)
        } else{
            val binding = ItemShopDisabledBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ShopItemViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)

        if (shopItem.enabled){
            holder.bindingEnabled.tvName.text = shopItem.name
            holder.bindingEnabled.tvCount.text = shopItem.count.toString()
        }else{
            holder.bindingDisabled.tvName.text = shopItem.name
            holder.bindingDisabled.tvCount.text = shopItem.count.toString()
        }

        holder.itemView.setOnLongClickListener{
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).enabled){
            VIEW_TYPE_ENABLED
        }else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object{
        const val VIEW_TYPE_ENABLED = 101
        const val VIEW_TYPE_DISABLED = 100

        const val MAX_POOL_SIZE = 30
    }
}