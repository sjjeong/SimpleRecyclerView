package com.dino.simplerecyclerview.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dino.simplerecyclerview.model.VectorAsset
import com.dino.simplerecyclerview.model.VectorAssetItem

class MvvmViewModel : ViewModel() {

    val vectorAssetItems = ObservableField<List<VectorAssetItem>>()

    val selectedItem = ObservableField<VectorAssetItem>()

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

    private fun showContent(item: VectorAssetItem) {
        selectedItem.set(item)
    }
}