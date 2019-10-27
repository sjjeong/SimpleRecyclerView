package com.dino.simplerecyclerview.model

import com.dino.simplerecyclerview.R

enum class VectorAsset(val resName: String, val resId: Int) {
    ANDROID("ANDROID", R.drawable.ic_android_black_24dp),
    CHEVRON_LEFT("CHEVRON_LEFT", R.drawable.ic_chevron_left_black_24dp),
    CHEVRON_RIGHT("CHEVRON_RIGHT", R.drawable.ic_chevron_right_black_24dp),
    DIALPAD("DIALPAD", R.drawable.ic_dialpad_black_24dp),
    FAST_FORWARD("FAST_FORWARD", R.drawable.ic_fast_forward_black_24dp),
    FAST_REWIND("FAST_REWIND", R.drawable.ic_fast_rewind_black_24dp),
    FAVORITE("FAVORITE", R.drawable.ic_favorite_black_24dp),
    PHONE("PHONE", R.drawable.ic_phone_black_24dp),
    PLACE("PLACE", R.drawable.ic_place_black_24dp),
    SEND("SEND", R.drawable.ic_send_black_24dp),
    THUMB_UP("THUMB_UP", R.drawable.ic_thumb_up_black_24dp),
    TIMER("TIMER", R.drawable.ic_timer_black_24dp),
    TRENDING_DOWN("TRENDING_DOWN", R.drawable.ic_trending_down_black_24dp),
    TRENDING_UP("TRENDING_UP", R.drawable.ic_trending_up_black_24dp),
    UNDO("UNDO", R.drawable.ic_undo_black_24dp),
    USB("USB", R.drawable.ic_usb_black_24dp),
    VIDEOCAM("VIDEOCAM", R.drawable.ic_videocam_black_24dp),
    WATCH("WATCH", R.drawable.ic_watch_black_24dp),
    WB_CLOUDY("WB_CLOUDY", R.drawable.ic_wb_cloudy_black_24dp),
    WIFI("WIFI", R.drawable.ic_wifi_black_24dp);

    fun toItem(onClick: (VectorAssetItem) -> Unit) = VectorAssetItem(resName, resId, onClick)
}

