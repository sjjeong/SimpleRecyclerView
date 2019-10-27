package com.dino.simplerecyclerview.mvp

import com.dino.simplerecyclerview.model.BaseVectorAssetItem

interface MvpContract {

    interface View {
        fun showItem(items: List<BaseVectorAssetItem>)

        fun showContent(item: BaseVectorAssetItem)
    }

    interface Presenter {
        fun createItem()
    }

}