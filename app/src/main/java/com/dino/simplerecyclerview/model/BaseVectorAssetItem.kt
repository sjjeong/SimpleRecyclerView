package com.dino.simplerecyclerview.model

import com.dino.library.dinorecyclerview.ItemViewType

interface BaseVectorAssetItem : ItemViewType {
    val resName: String
    val resId: Int
    val onClick: (BaseVectorAssetItem) -> Unit
    override val itemLayoutResId: Int
}