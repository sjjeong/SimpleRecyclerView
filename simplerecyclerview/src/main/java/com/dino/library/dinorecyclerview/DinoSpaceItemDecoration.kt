package com.dino.library.dinorecyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal class DinoSpaceItemDecoration(private val space: Int, private val includeEdge: Boolean) :
    RecyclerView.ItemDecoration() {


    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> {
                setGridLayoutSpace(layoutManager, outRect, position)
            }
            is StaggeredGridLayoutManager -> {
                setStaggeredGridLayoutSpace(layoutManager, view, outRect, position)
            }
            is LinearLayoutManager -> {
                setLinearLayoutSpace(layoutManager, outRect, position)
            }
        }

    }

    private fun setGridLayoutSpace(
        layoutManager: GridLayoutManager,
        outRect: Rect,
        position: Int
    ) {
        val spanCount = layoutManager.spanCount
        val column = position % spanCount
        setGridSpace(layoutManager.orientation, outRect, column, spanCount, position)
    }

    private fun setStaggeredGridLayoutSpace(
        layoutManager: StaggeredGridLayoutManager,
        view: View,
        outRect: Rect,
        position: Int
    ) {
        val spanCount = layoutManager.spanCount
        val column =
            (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex
        setGridSpace(layoutManager.orientation, outRect, column, spanCount, position)
    }

    private fun setGridSpace(
        @RecyclerView.Orientation orientation: Int,
        outRect: Rect,
        column: Int,
        spanCount: Int,
        position: Int
    ) {
        when (orientation) {
            RecyclerView.HORIZONTAL -> {
                if (includeEdge) {
                    outRect.run {
                        top = space - column * space / spanCount
                        bottom = (column + 1) * space / spanCount
                        if (position < spanCount) {
                            left = space
                        }
                        right = space
                    }
                } else {
                    outRect.run {
                        top = column * space / spanCount
                        bottom = space - (column + 1) * space / spanCount
                        if (position >= spanCount) {
                            left = space
                        }
                    }
                }
            }
            RecyclerView.VERTICAL -> {
                if (includeEdge) {
                    outRect.run {
                        left = space - column * space / spanCount
                        right = (column + 1) * space / spanCount
                        if (position < spanCount) {
                            top = space
                        }
                        bottom = space
                    }
                } else {
                    outRect.run {
                        left = column * space / spanCount
                        right = space - (column + 1) * space / spanCount
                        if (position >= spanCount) {
                            top = space
                        }
                    }
                }
            }
        }
    }

    private fun setLinearLayoutSpace(
        layoutManager: LinearLayoutManager,
        outRect: Rect,
        position: Int
    ) {
        when (layoutManager.orientation) {
            RecyclerView.HORIZONTAL -> {
                if (includeEdge) {
                    outRect.run {
                        if (position < 1) {
                            left = space
                        }
                        top = space
                        bottom = space
                        right = space
                    }
                } else {
                    outRect.run {
                        if (position >= 1) {
                            left = space
                        }
                    }
                }
            }
            RecyclerView.VERTICAL -> {
                if (includeEdge) {
                    outRect.run {
                        if (position < 1) {
                            top = space
                        }
                        left = space
                        right = space
                        bottom = space
                    }
                } else {
                    outRect.run {
                        if (position >= 1) {
                            top = space
                        }
                    }
                }
            }
        }

    }

}