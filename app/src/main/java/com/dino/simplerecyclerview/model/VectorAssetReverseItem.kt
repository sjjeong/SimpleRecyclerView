package com.dino.simplerecyclerview.model

import com.dino.simplerecyclerview.R

data class VectorAssetReverseItem(
    override val resName: String,
    override val resId: Int,
    override val onClick: (BaseVectorAssetItem) -> Unit,
    override val itemLayoutResId: Int = R.layout.item_vector_asset_reverse
) : BaseVectorAssetItem