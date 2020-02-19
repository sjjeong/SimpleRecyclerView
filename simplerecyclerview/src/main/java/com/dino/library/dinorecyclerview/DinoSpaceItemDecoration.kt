package com.dino.library.dinorecyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

internal class DinoSpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    private val halfSpace = space / 2

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


        val startLineSpace = if (position / spanCount == 0) {
            0
        } else {
            halfSpace
        }

        val endLineSpace = if (position / spanCount == (count - 1) / spanCount) {
            0
        } else {
            halfSpace
        }

        when (position % spanCount) {
            0 -> {
                // start position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(startLineSpace, 0, endLineSpace, halfSpace)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(0, startLineSpace, halfSpace, endLineSpace)
                    }
                }
            }
            spanCount - 1 -> {
                // end position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(startLineSpace, halfSpace, endLineSpace, 0)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(halfSpace, startLineSpace, 0, endLineSpace)
                    }
                }
            }
            else -> {
                // middle position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(startLineSpace, halfSpace, endLineSpace, halfSpace)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(halfSpace, startLineSpace, halfSpace, endLineSpace)
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

        val startLineSpace = if (position / spanCount == 0) {
            0
        } else {
            halfSpace
        }

        val endLineSpace = if (position / spanCount == (count - 1) / spanCount) {
            0
        } else {
            halfSpace
        }


        when (spanIndex) {
            0 -> {
                // start position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(startLineSpace, 0, endLineSpace, halfSpace)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(0, startLineSpace, halfSpace, endLineSpace)
                    }
                }
            }
            spanCount - 1 -> {
                // end position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(startLineSpace, halfSpace, endLineSpace, 0)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(halfSpace, startLineSpace, 0, endLineSpace)
                    }
                }
            }
            else -> {
                // middle position
                when (layoutManager.orientation) {
                    RecyclerView.HORIZONTAL -> {
                        outRect.set(startLineSpace, halfSpace, endLineSpace, halfSpace)
                    }
                    RecyclerView.VERTICAL -> {
                        outRect.set(halfSpace, startLineSpace, halfSpace, endLineSpace)
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