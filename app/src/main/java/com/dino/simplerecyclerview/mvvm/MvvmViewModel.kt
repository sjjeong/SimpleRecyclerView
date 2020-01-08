package com.dino.simplerecyclerview.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dino.simplerecyclerview.ItemClickListener
import com.dino.simplerecyclerview.model.BaseVectorAssetItem
import com.dino.simplerecyclerview.model.VectorAsset

class MvvmViewModel : ViewModel(), ItemClickListener {

    val vectorAssetItems = ObservableField<List<BaseVectorAssetItem>>()

    val selectedItem = ObservableField<BaseVectorAssetItem>()

    init {
        createNewItem()
    }

    fun createNewItem() {
        vectorAssetItems.set(
            VectorAsset.values()
                .map { it.toItem() }
                .toMutableList()
                .shuffled())
    }

    override fun onClick(item: BaseVectorAssetItem) {
        selectedItem.set(item)
    }
}