package com.dino.simplerecyclerview.mvvm

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.dino.simplerecyclerview.model.VectorAsset

class MvvmViewModel : ViewModel() {

    val vectorAssetItems = ObservableField<List<VectorAsset>>()

    init {
        createNewItem()
    }

    fun createNewItem() {
        vectorAssetItems.set(VectorAsset.values().toMutableList().shuffled())
    }
}