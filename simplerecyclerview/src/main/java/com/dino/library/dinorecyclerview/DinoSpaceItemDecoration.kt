package com.dino.library.dinorecyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal class DinoSpaceItemDecoration(val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter?.itemCount ?: 0

        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> {
                setGridLayoutSpace(layoutManager, outRect, count, position)
            }
            is StaggeredGridLayoutManager -> {
                setStaggeredGridLayoutSpace(layoutManager, view, outRect, count, position)
            }
            is LinearLayoutManager -> {
                setLinearLayoutSpace(layoutManager, outRect, count, position)
            }
        }

    }

    private fun setGridLayoutSpace(
        layoutManager: GridLayoutManager,
        outRect: Rect,
        count: Int,
        position: Int
    ) {
        val spanCount = layoutManager.spanCount

        val endLineSpace =
            if (position / spanCount == (count - 1) / spanCount) {
                0
            } else {
                space
            }

        when (position % spanCount) {
            spanCount - 1 -> {
                // end position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(0, 0, endLineSpace, 0)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(0, 0, 0, endLineSpace)
                    }
                }
            }
            else -> {
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(0, 0, endLineSpace, space)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(0, 0, space, endLineSpace)
                    }
                }
            }
        }
    }

    private fun setStaggeredGridLayoutSpace(
        layoutManager: StaggeredGridLayoutManager,
        view: View,
        outRect: Rect,
        count: Int,
        position: Int
    ) {
        val spanCount = layoutManager.spanCount
        val spanIndex =
            (view.layoutParams as StaggeredGridLayoutManager.LayoutParams).spanIndex

        val endLineSpace =
            if (position / spanCount == (count - 1) / spanCount) {
                0
            } else {
                space
            }

        when (spanIndex) {
            spanCount - 1 -> {
                // end position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(0, 0, endLineSpace, 0)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(0, 0, 0, endLineSpace)
                    }
                }
            }
            else -> {
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(0, 0, endLineSpace, space)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(0, 0, space, endLineSpace)
                    }
                }
            }
        }
    }

    private fun setLinearLayoutSpace(
        layoutManager: LinearLayoutManager,
        outRect: Rect,
        count: Int,
        position: Int
    ) {
        if (position == count - 1) {
            return
        }
        when (layoutManager.orientation) {
            RecyclerView.HORIZONTAL -> {
                outRect.set(0, 0, space, 0)
            }
            RecyclerView.VERTICAL -> {
                outRect.set(0, 0, 0, space)
            }
        }
    }

}