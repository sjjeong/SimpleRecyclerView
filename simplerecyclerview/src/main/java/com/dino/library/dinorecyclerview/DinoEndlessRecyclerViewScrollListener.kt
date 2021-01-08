package com.dino.library.dinorecyclerview

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * https://github.com/codepath/android_guides/wiki/Endless-Scrolling-with-AdapterViews-and-RecyclerView
 */
internal class DinoEndlessRecyclerViewScrollListener(
    private val layoutManager: RecyclerView.LayoutManager,
    defaultVisibleThreshold: Int = DEFAULT_VISIBLE_THRESHOLD,
    private val onLoad: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var previousTotalItemCount = 0
    private var loading = true

    private val visibleThreshold = when (layoutManager) {
        is LinearLayoutManager -> {
            defaultVisibleThreshold
        }
        is GridLayoutManager -> {
            defaultVisibleThreshold * layoutManager.spanCount
        }
        is StaggeredGridLayoutManager -> {
            defaultVisibleThreshold * layoutManager.spanCount
        }
        else -> {
            error(DO_NOT_SUPPORT_LAYOUT_MANAGER_ERROR_MSG)
        }
    }

    private val lastVisibleItemPosition: Int
        get() = when (layoutManager) {
            is LinearLayoutManager -> {
                layoutManager.findLastVisibleItemPosition()
            }
            is StaggeredGridLayoutManager -> {
                layoutManager.findLastVisibleItemPositions(null).maxOrNull() ?: 0
            }
            else -> {
                error(DO_NOT_SUPPORT_LAYOUT_MANAGER_ERROR_MSG)
            }
        }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val totalItemCount = layoutManager.itemCount

        if (totalItemCount < previousTotalItemCount) {
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold >= totalItemCount) {
            loading = true
            onLoad()
        }
    }

    companion object {
        internal const val DEFAULT_VISIBLE_THRESHOLD = 5
    }

}
