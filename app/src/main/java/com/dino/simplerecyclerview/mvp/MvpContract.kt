package com.dino.simplerecyclerview.mvp

import com.dino.simplerecyclerview.model.VectorAssetItem

interface MvpContract {

    interface View {
        fun showItem(items: List<VectorAssetItem>)

        fun showContent(item: VectorAssetItem)
    }

    interface Presenter {
        fun createItem()
    }

}