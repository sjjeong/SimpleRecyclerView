package com.dino.library.dinorecyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class DinoAdapter(
    @LayoutRes private val layoutResId: Int?
) : RecyclerView.Adapter<DinoViewHolder>() {
    var eventHolder: Any? = null

    protected open val items = mutableListOf<Any>()

    open fun replaceAll(items: List<Any>?) {
        this.items.run {
            clear()
            items?.let {
                addAll(it)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DinoViewHolder(layoutResId = viewType, parent = parent)

    override fun getItemCount() =
        items.size

    override fun onBindViewHolder(holder: DinoViewHolder, position: Int) =
        holder.onBindViewHolder(items[position], eventHolder)

    override fun getItemViewType(position: Int) =
        when (val item = items[position]) {
            is ItemViewType -> item.itemLayoutResId
            else -> layoutResId ?: error(ADAPTER_CANNOT_CREATED_ERROR_MSG)
        }

}