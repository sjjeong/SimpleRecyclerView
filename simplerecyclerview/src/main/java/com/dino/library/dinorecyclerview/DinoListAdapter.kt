package com.dino.library.dinorecyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil

open class DinoListAdapter(
    @LayoutRes private val layoutResId: Int?,
    diffCallback: DiffUtil.ItemCallback<Any>
) : androidx.recyclerview.widget.ListAdapter<Any, DinoViewHolder>(diffCallback) {
    var eventHolder: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DinoViewHolder(layoutResId = viewType, parent = parent)

    override fun onBindViewHolder(holderDino: DinoViewHolder, position: Int) =
        holderDino.onBindViewHolder(getItem(position), eventHolder)

    override fun getItemViewType(position: Int) =
        when (val item = getItem(position)) {
            is ItemViewType -> item.itemLayoutResId
            else -> layoutResId ?: error(ADAPTER_CANNOT_CREATED_ERROR_MSG)
        }

}