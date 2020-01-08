package com.dino.simplerecyclerview.normal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.dino.simplerecyclerview.ItemClickListener
import com.dino.simplerecyclerview.R
import com.dino.simplerecyclerview.base.BaseDataBindingActivity
import com.dino.simplerecyclerview.databinding.ActivityNormalBinding
import com.dino.simplerecyclerview.model.BaseVectorAssetItem
import com.dino.simplerecyclerview.model.VectorAsset

class NormalActivity : BaseDataBindingActivity<ActivityNormalBinding>(R.layout.activity_normal),
    ItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.eventHolder = this@NormalActivity
        showItem()
    }

    fun onRefreshClick(view: View) {
        showItem()
    }

    private fun showItem() {
        binding.items =
            VectorAsset.values()
                .map { it.toItem() }
                .toMutableList()
                .shuffled()
    }

    override fun onClick(item: BaseVectorAssetItem) {
        Toast.makeText(this, item.resName, Toast.LENGTH_SHORT).show()
    }

}