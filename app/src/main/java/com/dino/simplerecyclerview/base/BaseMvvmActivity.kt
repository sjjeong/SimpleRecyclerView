package com.dino.simplerecyclerview.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.dino.simplerecyclerview.BR

abstract class BaseMvvmActivity<B : ViewDataBinding>(
    layoutResId: Int,
    private val viewModelCls: Class<out ViewModel>
) : AppCompatActivity(layoutResId) {

    protected val binding by lazy {
        DataBindingUtil.bind<B>(
            (window.decorView
                .findViewById(android.R.id.content) as ViewGroup).getChildAt(0)
        )!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding {
            setVariable(BR.vm, ViewModelProviders.of(this@BaseMvvmActivity)[viewModelCls])
        }
    }

    protected fun binding(action: B.() -> Unit) {
        binding.run(action)
    }

}