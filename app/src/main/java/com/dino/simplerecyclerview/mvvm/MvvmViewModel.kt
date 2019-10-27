package com.dino.simplerecyclerview.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dino.simplerecyclerview.model.BaseVectorAssetItem
import com.dino.simplerecyclerview.model.VectorAsset

class MvvmViewModel : ViewModel() {

    val vectorAssetItems = ObservableField<List<BaseVectorAssetItem>>()

    val selectedItem = ObservableField<BaseVectorAssetItem>()

    init {
        createNewItem()
    }

    fun createNewItem() {
        vectorAssetItems.set(
            VectorAsset.values()
                .map { it.toItem(::showContent) }
                .toMutableList()
                .shuffled())
    }

    private fun showContent(item: BaseVectorAssetItem) {
        selectedItem.set(item)
    }
}