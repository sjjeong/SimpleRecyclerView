package com.dino.simplerecyclerview

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imgResId")
fun ImageView.setDrawableResId(resId: Int) {
    setImageResource(resId)
}