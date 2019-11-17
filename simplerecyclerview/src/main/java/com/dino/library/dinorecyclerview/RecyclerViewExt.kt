package com.dino.library.dinorecyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * dino_items="@{vm.liveMainItem}"
 */
@BindingAdapter("dino_items")
fun RecyclerView.setItems(dino_items: List<Any>?) {
    (adapter as? DinoAdapter)?.replaceAll(dino_items)
    (adapter as? DinoListAdapter)?.submitList(dino_items)
}

/**
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_items")
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
 * dino_diffCallback="@{(Object)MainItem.Companion}"
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_diffCallback", "dino_items")
fun RecyclerView.setDinoListAdapter(
    @LayoutRes dino_itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    items: List<Any>?
) {
    val simpleListAdapter =
        this.adapter as? DinoListAdapter
            ?: DinoListAdapter(dino_itemLayout, diffCallback).also {
                this.adapter = it
            }
    simpleListAdapter.submitList(items)
}


@BindingAdapter("dino_itemSpace")
fun RecyclerView.setItemSpace(space: Float) {
    val loop = itemDecorationCount
    for (i in 0 until loop) {
        val itemDecoration = getItemDecorationAt(i)
        if (itemDecoration is DinoSpaceItemDecoration) {
            removeItemDecorationAt(i)
            break
        }
    }
    val spacePixel = context.resources.displayMetrics.density * space
    addItemDecoration(DinoSpaceItemDecoration(spacePixel.toInt()))
}