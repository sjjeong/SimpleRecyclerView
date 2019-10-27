package com.dino.simplerecyclerview.model

data class VectorAssetItem(
    val resName: String,
    val resId: Int,
    val onClick: (VectorAssetItem) -> Unit
)