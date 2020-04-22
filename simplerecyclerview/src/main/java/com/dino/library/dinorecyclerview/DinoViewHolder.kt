package com.dino.library.dinorecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView

open class DinoViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context)
        .inflate(layoutResId, parent, false)
), LifecycleOwner {

    protected val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    open fun onBindViewHolder(item: Any?, eventHolder: Any?) {
        try {
            binding.run {
                lifecycleOwner = this@DinoViewHolder
                setVariable(BR.item, item)
                setVariable(BR.eventHolder, eventHolder)
                setVariable(BR.vm, eventHolder)
                setVariable(BR.viewModel, eventHolder)
                setVariable(BR.adapterPosition, adapterPosition)
                executePendingBindings()
            }
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
        }
    }

    fun onAttach() {
        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    fun onDetach() {
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

}