package com.dino.simplerecyclerview.mvvm

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import com.dino.simplerecyclerview.R
import com.dino.simplerecyclerview.base.BaseMvvmActivity
import com.dino.simplerecyclerview.databinding.ActivityMvvmBinding
import com.dino.simplerecyclerview.model.BaseVectorAssetItem

class MvvmActivity :
    BaseMvvmActivity<ActivityMvvmBinding>(R.layout.activity_mvvm, MvvmViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm?.selectedItem?.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val item = binding.vm?.selectedItem?.get() ?: return
                showContent(item)
            }
        })
    }

    private fun showContent(item: BaseVectorAssetItem) {
        Toast.makeText(this, item.resName, Toast.LENGTH_SHORT).show()
    }

}