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
    setDinoAdapterWithEventHolder(adapterItemId, items, null)
}

/**
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 * dino_eventHolder="@{eventHolder}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_items", "dino_eventHolder")
fun RecyclerView.setDinoAdapterWithEventHolder(
    @LayoutRes adapterItemId: Int,
    items: List<Any>?,
    eventHolder: Any?
) {
    val simpleAdapter =
        this.adapter as? DinoAdapter
            ?: DinoAdapter(adapterItemId, eventHolder).also {
                this.adapter = it
            }
    simpleAdapter.replaceAll(items)
}

/**
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 * dino_vm="@{vm}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_items", "dino_vm")
fun RecyclerView.setDinoAdapterWithVm(
    @LayoutRes adapterItemId: Int,
    items: List<Any>?,
    vm: Any?
) {
    setDinoAdapterWithEventHolder(adapterItemId, items, vm)
}

/**
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 * dino_viewModel="@{viewModel}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_items", "dino_viewModel")
fun RecyclerView.setDinoAdapterWithViewModel(
    @LayoutRes adapterItemId: Int,
    items: List<Any>?,
    viewModel: Any?
) {
    setDinoAdapterWithEventHolder(adapterItemId, items, viewModel)
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
    setDinoListAdapterWithEventHolder(dino_itemLayout, diffCallback, items, null)
}

/**
 * dino_diffCallback="@{(Object)MainItem.Companion}"
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 * dino_eventHolder="@{eventHolder}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_diffCallback", "dino_items", "dino_eventHolder")
fun RecyclerView.setDinoListAdapterWithEventHolder(
    @LayoutRes dino_itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    items: List<Any>?,
    eventHolder: Any?
) {
    val simpleListAdapter =
        this.adapter as? DinoListAdapter
            ?: DinoListAdapter(dino_itemLayout, diffCallback, eventHolder).also {
                this.adapter = it
            }
    simpleListAdapter.submitList(items)
}

/**
 * dino_diffCallback="@{(Object)MainItem.Companion}"
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 * dino_vm="@{vm}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_diffCallback", "dino_items", "dino_vm")
fun RecyclerView.setDinoListAdapterWithVm(
    @LayoutRes dino_itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    items: List<Any>?,
    vm: Any?
) {
    setDinoListAdapterWithEventHolder(dino_itemLayout, diffCallback, items, vm)
}

/**
 * dino_diffCallback="@{(Object)MainItem.Companion}"
 * dino_itemLayout="@{@layout/item_main}"
 * dino_items="@{vm.liveMainItem}"
 * dino_viewModel="@{viewModel}
 */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("dino_itemLayout", "dino_diffCallback", "dino_items", "dino_viewModel")
fun RecyclerView.setDinoListAdapterWithViewModel(
    @LayoutRes dino_itemLayout: Int,
    diffCallback: DiffUtil.ItemCallback<Any>,
    items: List<Any>?,
    viewModel: Any?
) {
    setDinoListAdapterWithEventHolder(dino_itemLayout, diffCallback, items, viewModel)
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