package com.dino.simplerecyclerview.model

import com.dino.library.dinorecyclerview.ItemViewType

interface BaseVectorAssetItem : ItemViewType {
    val resName: String
    val resId: Int
    override val itemLayoutResId: Int
}