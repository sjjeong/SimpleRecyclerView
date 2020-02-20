package com.dino.simplerecyclerview.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dino.simplerecyclerview.ItemClickListener
import com.dino.simplerecyclerview.model.BaseVectorAssetItem
import com.dino.simplerecyclerview.model.VectorAsset
import kotlin.random.Random

class MvvmViewModel : ViewModel(), ItemClickListener {

    val vectorAssetItems = ObservableField<List<BaseVectorAssetItem>>()

    val selectedItem = ObservableField<BaseVectorAssetItem>()

    val randomNumer = ObservableField<Long>(0)

    init {
        createNewItem()
    }

    fun createNewItem() {
        vectorAssetItems.set(
            VectorAsset.values()
                .map { it.toItem() }
                .toMutableList()
                .shuffled())
        randomNumer.set(Random.nextLong(1000))
    }

    override fun onClick(item: BaseVectorAssetItem) {
        selectedItem.set(item)
    }
}