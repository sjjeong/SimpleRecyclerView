package com.dino.simplerecyclerview.mvp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dino.simplerecyclerview.R
import com.dino.simplerecyclerview.base.BaseDataBindingActivity
import com.dino.simplerecyclerview.databinding.ActivityMvpBinding
import com.dino.simplerecyclerview.model.BaseVectorAssetItem

class MvpActivity : BaseDataBindingActivity<ActivityMvpBinding>(R.layout.activity_mvp),
    MvpContract.View {

    private val presenter: MvpContract.Presenter = MvpPresenter(this@MvpActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.createItem()
    }

    override fun showItem(items: List<BaseVectorAssetItem>) {
        binding.items = items
    }

    fun onRefreshClick(view: View) {
        presenter.createItem()
    }

    override fun showContent(item: BaseVectorAssetItem) {
        Toast.makeText(this, item.resName, Toast.LENGTH_SHORT).show()
    }


}