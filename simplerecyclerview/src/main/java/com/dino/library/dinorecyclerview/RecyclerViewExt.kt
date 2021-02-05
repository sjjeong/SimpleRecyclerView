package com.dino.library.dinorecyclerview

import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ListenerUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @param items             dino_items="@{vm.itemList}"
 * @param layoutResId       dino_itemLayout="@{@layout/item_main}"
 * @param diffCallback      dino_diffCallback="@{(Object)MainItem.Companion}"
 * @param eventHolder       dino_eventHolder="@{eventHolder}
 * @param vm                dino_vm="@{vm}
 * @param viewModel         dino_viewModel="@{viewModel}
 * @param headerItem        dino_headerItem="@{vm.headerItem}"
 * @param headerLayoutResId dino_headerItemLayout="@{@layout/item_header}"
 * @param footerItem        dino_footerItem="@{vm.footerItem}"
 * @param footerLayoutResId dino_footerItemLayout="@{@layout/item_footer}"
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

/**
 * @param space       dino_itemSpace="@{@dimen/space}"
 * @param includeEdge dino_includeEdge="@{true}"
 */
@BindingAdapter("dino_itemSpace", "dino_includeEdge", requireAll = false)
fun RecyclerView.setItemSpace(space: Float = 0f, includeEdge: Boolean = false) {
    val itemDecoration = DinoSpaceItemDecoration(space.toInt(), includeEdge)
    ListenerUtil.trackListener(
        this,
        itemDecoration,
        R.id.dino_itemSpace
    )?.let(::removeItemDecoration)
    addItemDecoration(DinoSpaceItemDecoration(space.toInt(), includeEdge))
}

/**
 * @param space       dino_itemSpace="@{`8dp`}"
 * @param includeEdge dino_includeEdge="@{true}"
 */
@BindingAdapter("dino_itemSpace", "dino_includeEdge", requireAll = false)
fun RecyclerView.setItemSpace(space: String = "0dp", includeEdge: Boolean = false) {
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
    setItemSpace(spacePixel, includeEdge)
}

/**
 * @param onLoad    dino_onLoad="@{() -> vm.loadItem()}"
 * @param threshold dino_threshold="@{10}"
 */
@BindingAdapter(
    "dino_onLoad",
    "dino_threshold",
    requireAll = false
)
fun RecyclerView.set(
    onLoad: (() -> Unit)? = null,
    threshold: Int = DinoEndlessRecyclerViewScrollListener.DEFAULT_VISIBLE_THRESHOLD
) {
    onLoad ?: error(NEED_ON_LOAD_EVENT_HANDLING)
    val layoutManager = layoutManager ?: error(NEED_RECYCLER_VIEW_LAYOUT_MANAGER_ERROR_MSG)
    val scrollListener = DinoEndlessRecyclerViewScrollListener(layoutManager, threshold, onLoad)

    ListenerUtil.trackListener(
        this,
        scrollListener,
        R.id.dino_onScrollListener
    )?.let(::removeOnScrollListener)

    addOnScrollListener(scrollListener)
}
