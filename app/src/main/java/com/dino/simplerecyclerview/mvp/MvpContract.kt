package com.dino.simplerecyclerview.mvp

import com.dino.simplerecyclerview.model.VectorAsset

interface MvpContract {

    interface View {
        fun showItem(items: List<VectorAsset>)
    }

    interface Presenter {
        fun createItem()
    }

}