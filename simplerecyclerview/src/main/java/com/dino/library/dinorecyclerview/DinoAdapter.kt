package com.dino.library.dinorecyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class DinoAdapter(
    @LayoutRes private val layoutRes: Int
) : RecyclerView.Adapter<DinoViewHolder>() {

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
        DinoViewHolder(layoutRes = viewType, parent = parent)

    override fun getItemCount() =
        items.size

    override fun onBindViewHolder(holder: DinoViewHolder, position: Int) =
        holder.onBindViewHolder(items[position])

    override fun getItemViewType(position: Int) =
        when (val item = items[position]) {
            is ItemViewType -> item.itemLayoutResId
            else -> layoutRes
        }

}