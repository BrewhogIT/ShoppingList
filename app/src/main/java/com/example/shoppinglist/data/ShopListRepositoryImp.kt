package com.example.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImp : ShopListRepository {
    private val _shopList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0
    private val shopList = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 .. 10){
            val item = ShopItem("Name $i", i, true)
            _shopList.add(item)
            shopList.value = _shopList
        }
    }

    override fun addShopItem(item: ShopItem) {
        if (item.id == ShopItem.UNDEFINED_ID){
            item.id = autoIncrementId++
        }

        _shopList.add(item)
        updateList()
    }

    override fun deleteShopItem(item: ShopItem) {
        _shopList.remove(item)
        updateList()
    }

    override fun editShopItem(item: ShopItem) {
        val oldItem = getShopItem(item.id)
        deleteShopItem(oldItem)
        addShopItem(item)
    }

    override fun getShopItem(id: Int): ShopItem {
        return _shopList.find { it.id == id } ?: throw RuntimeException("Wrong id ()  $id ")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopList
    }

    fun updateList(){
        shopList.value = _shopList.toList()
    }
}