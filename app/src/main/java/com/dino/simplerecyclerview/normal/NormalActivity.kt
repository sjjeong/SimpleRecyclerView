package com.dino.simplerecyclerview.normal

import android.os.Bundle
import android.view.View
import com.dino.simplerecyclerview.R
import com.dino.simplerecyclerview.base.BaseDataBindingActivity
import com.dino.simplerecyclerview.databinding.ActivityNormalBinding
import com.dino.simplerecyclerview.model.VectorAsset

class NormalActivity : BaseDataBindingActivity<ActivityNormalBinding>(R.layout.activity_normal) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showItem()
    }

    fun onRefreshClick(view: View) {
        showItem()
    }

    private fun showItem() {
        binding.items = VectorAsset.values().toMutableList().shuffled()
    }

}