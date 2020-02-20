package com.dino.library.dinorecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class DinoViewHolder(
    @LayoutRes layoutResId: Int,
    parent: ViewGroup?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent?.context)
        .inflate(layoutResId, parent, false)
) {

    protected val binding: ViewDataBinding = DataBindingUtil.bind(itemView)!!

    open fun onBindViewHolder(item: Any?, eventHolder: Any?) {
        try {
            binding.run {
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
}