package com.example.shoppinglist.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.ItemShopDisabledBinding
import com.example.shoppinglist.databinding.ItemShopEnabledBinding

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