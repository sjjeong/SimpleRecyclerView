package com.dino.library.dinorecyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * dino_items="@{vm.itemList}"
 * dino_itemLayout="@{@layout/item_main}"
 * dino_diffCallback="@{(Object)MainItem.Companion}"
 * dino_eventHolder="@{eventHolder}
 * dino_vm="@{vm}
 * dino_viewModel="@{viewModel}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter(
    "dino_items",
    "dino_itemLayout",
    "dino_diffCallback",
    "dino_eventHolder",
    "dino_vm",
    "dino_viewModel",
    "dino_headerItem",
    "dino_headerItemLayout",
    "dino_footerItem",
    "dino_footerItemLayout",
    requireAll = false
)
fun RecyclerView.setDinoAdapter(
    items: List<Any>? = null,
    @LayoutRes layoutResId: Int? = null,
    diffCallback: DiffUtil.ItemCallback<Any>? = null,
    eventHolder: Any? = null,
    vm: Any? = null,
    viewModel: Any? = null,
    headerItem: Any? = null,
    @LayoutRes headerLayoutResId: Int? = null,
    footerItem: Any? = null,
    @LayoutRes footerLayoutResId: Int? = null
) {
    if (diffCallback == null) {
        // DinoAdapter
        val simpleAdapter = this.adapter as? DinoAdapter
            ?: DinoAdapter(layoutResId).also {
                it.eventHolder = eventHolder ?: vm ?: viewModel
                this.adapter = it
            }
        simpleAdapter.headerItem = headerItem
        simpleAdapter.headerLayoutResId = headerLayoutResId
        simpleAdapter.footerItem = footerItem
        simpleAdapter.footerLayoutResId = footerLayoutResId
        simpleAdapter.replaceAll(items)
    } else {
        // DinoListAdapter
        val simpleListAdapter = this.adapter as? DinoListAdapter
            ?: DinoListAdapter(layoutResId, diffCallback).also {
                it.eventHolder = eventHolder ?: vm ?: viewModel
                this.adapter = it
            }
        simpleListAdapter.headerItem = headerItem
        simpleListAdapter.headerLayoutResId = headerLayoutResId
        simpleListAdapter.footerItem = footerItem
        simpleListAdapter.footerLayoutResId = footerLayoutResId
        simpleListAdapter.submitList(items)
    }
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
    addItemDecoration(DinoSpaceItemDecoration(space.toInt()))
}

@BindingAdapter("dino_itemSpace")
fun RecyclerView.setItemSpace(space: String) {
    if (!space.contains("dp")) {
        return
    }
    val dpSpace = try {
        space.dropLast(2).toFloat()
    } catch (e: Exception) {
        e.printStackTrace()
        0f
    }
    val spacePixel = context.resources.displayMetrics.density * dpSpace
    setItemSpace(spacePixel)
}