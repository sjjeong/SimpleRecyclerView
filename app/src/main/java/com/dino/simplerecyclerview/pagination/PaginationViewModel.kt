package com.dino.simplerecyclerview.pagination

import androidx.databinding.ObservableField
import kotlin.random.Random

class PaginationViewModel {

    val items = ObservableField<List<Int>>()
    val isRefresh = ObservableField(false)

    init {
        loadItem()
    }

    fun loadItem() {
        isRefresh.set(true)
        val itemList = (items.get() ?: listOf()).toMutableList()
        repeat(20) {
            itemList.add(Random.nextInt(Int.MAX_VALUE))
        }
        isRefresh.set(false)
        items.set(itemList)
    }

    fun reset() {
        items.set(listOf())
        loadItem()
    }
}
