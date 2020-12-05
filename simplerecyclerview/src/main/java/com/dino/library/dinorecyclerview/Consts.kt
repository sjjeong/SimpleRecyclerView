package com.dino.library.dinorecyclerview

internal const val ADAPTER_CANNOT_CREATED_ERROR_MSG =
    "RecyclerView.Adapter cannot be created because there is no ViewHolder item layout.\n You must add \"dino_itemLayout\" attribute in RecyclerView or implement ItemViewType interface to your item class."

internal const val NEED_ON_LOAD_EVENT_HANDLING =
    "If you want to pagination, you must add \"dino_onLoad\" attribute"

internal const val NEED_RECYCLER_VIEW_LAYOUT_MANAGER_ERROR_MSG =
    "If you want to use \"dino_onLoad\" attr, you must add RecyclerView.LayoutManager like LinearLayoutManager or GridLayoutManager or StaggeredGridLayoutManager"

internal const val DO_NOT_SUPPORT_LAYOUT_MANAGER_ERROR_MSG =
    "This LayoutManager is not supported. Please use LinearLayoutManager or GridLayoutManager or StaggeredGridLayoutManager"