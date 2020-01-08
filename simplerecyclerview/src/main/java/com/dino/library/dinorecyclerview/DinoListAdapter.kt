package com.dino.library.dinorecyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil

open class DinoListAdapter(
    @LayoutRes private val layoutRes: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    private val eventHolder: Any? = null
) : androidx.recyclerview.widget.ListAdapter<Any, DinoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DinoViewHolder(layoutRes = viewType, parent = parent)

    override fun onBindViewHolder(holderDino: DinoViewHolder, position: Int) =
        holderDino.onBindViewHolder(getItem(position), eventHolder)

    override fun getItemViewType(position: Int) =
        when (val item = getItem(position)) {
            is ItemViewType -> item.itemLayoutResId
            else -> layoutRes
        }

}