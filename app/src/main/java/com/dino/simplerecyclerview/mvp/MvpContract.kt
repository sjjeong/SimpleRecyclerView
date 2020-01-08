package com.dino.simplerecyclerview.mvp

import com.dino.simplerecyclerview.model.BaseVectorAssetItem

interface MvpContract {

    interface View {
        fun showItem(items: List<BaseVectorAssetItem>)
    }

    interface Presenter {
        fun createItem()
    }

}