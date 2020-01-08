package com.dino.simplerecyclerview.model

import com.dino.simplerecyclerview.R

data class VectorAssetItem(
    override val resName: String,
    override val resId: Int,
    override val itemLayoutResId: Int = R.layout.item_vector_asset
) : BaseVectorAssetItem