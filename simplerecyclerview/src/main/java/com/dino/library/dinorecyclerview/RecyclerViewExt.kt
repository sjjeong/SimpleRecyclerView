package com.dino.library.dinorecyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * items="@{vm.liveMainItem}"
 */
@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Any>?) {
    (adapter as? DinoAdapter)?.replaceAll(items)
    (adapter as? DinoListAdapter)?.submitList(items)
}

/**
 * itemLayout="@{@layout/item_main}"
 * items="@{vm.liveMainItem}"
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("itemLayout", "items")
fun RecyclerView.setDinoAdapter(
    @LayoutRes adapterItemId: Int,
    items: List<Any>?
) {
    val simpleAdapter =
        this.adapter as? DinoAdapter
            ?: DinoAdapter(adapterItemId).also {
                this.adapter = it
            }
    simpleAdapter.replaceAll(items)
}

/**
 * diffCallback="@{(Object)MainItem.Companion}"
 * itemLayout="@{@layout/item_main}"
 * items="@{vm.liveMainItem}"
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("itemLayout", "diffCallback", "items")
fun RecyclerView.setDinoListAdapter(
    @LayoutRes itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    items: List<Any>?
) {
    val simpleListAdapter =
        this.adapter as? DinoListAdapter
            ?: DinoListAdapter(itemLayout, diffCallback).also {
                this.adapter = it
            }
    simpleListAdapter.submitList(items)
}
