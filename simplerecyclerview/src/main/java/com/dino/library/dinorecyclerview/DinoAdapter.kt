package com.dino.library.dinorecyclerview

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class DinoAdapter(
    @LayoutRes private val layoutResId: Int?
) : RecyclerView.Adapter<DinoViewHolder>() {
    var eventHolder: Any? = null

    private var headerSize: Int = 0
    @LayoutRes
    var headerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            headerSize = if (value == null) 0 else 1
        }
    var headerItem: Any? = null

    private var footerSize: Int = 0
    @LayoutRes
    var footerLayoutResId: Int? = null
        set(value) {
            if (value == field) {
                return
            }
            field = value
            footerSize = if (value == null) 0 else 1
        }
    var footerItem: Any? = null

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

    override fun getItemCount(): Int {
        return items.size + headerSize + footerSize
    }

    override fun onBindViewHolder(holder: DinoViewHolder, position: Int) {
        val item = when {
            isHeaderPosition(position) -> headerItem
            isFooterPosition(position) -> footerItem
            else -> items[position - headerSize]
        }
        holder.onBindViewHolder(item, eventHolder)
    }

    override fun onViewAttachedToWindow(holder: DinoViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewDetachedFromWindow(holder: DinoViewHolder) {
        holder.onDetach()
        super.onViewDetachedFromWindow(holder)
    }

    override fun getItemViewType(position: Int): Int {
        if (isHeaderPosition(position)) {
            return headerLayoutResId!!
        }
        if (isFooterPosition(position)) {
            return footerLayoutResId!!
        }
        return when (val item = items[position - headerSize]) {
            is ItemViewType -> item.itemLayoutResId
            else -> layoutResId ?: error(ADAPTER_CANNOT_CREATED_ERROR_MSG)
        }
    }

    private fun isHeaderPosition(position: Int) = headerSize != 0 && position == 0

    private fun isFooterPosition(position: Int) = footerSize != 0 && position == itemCount - 1


}