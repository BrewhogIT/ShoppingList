package com.example.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ItemShopDisabledBinding
import com.example.shoppinglist.databinding.ItemShopEnabledBinding
import com.example.shoppinglist.domain.ShopItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {
    var onShopItemLongClickListener : ((ShopItem) -> Unit)? = null
    var onShopItemClickListener : ((ShopItem)->Unit)? = null

    var shopItemList = emptyList<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ShopItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var bindingEnabled : ItemShopEnabledBinding
        lateinit var bindingDisabled : ItemShopDisabledBinding

        constructor(b: ItemShopEnabledBinding) : this(b.root){
            bindingEnabled = b
        }

        constructor(b: ItemShopDisabledBinding) : this(b.root){
            bindingDisabled = b
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return if (viewType == VIEW_TYPE_ENABLED){
            val binding = ItemShopEnabledBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ShopItemViewHolder(binding)
        } else{
            val binding = ItemShopDisabledBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            ShopItemViewHolder(binding)
        }
    }

    override fun getItemCount(): Int {
        return shopItemList.size
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopItemList[position]

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
        return if(shopItemList[position].enabled){
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