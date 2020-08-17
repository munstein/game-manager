package com.munstein.gamemanager.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalMarginDecoration(val marginInPx: Int = 0) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = marginInPx
        outRect.right = marginInPx
    }
}