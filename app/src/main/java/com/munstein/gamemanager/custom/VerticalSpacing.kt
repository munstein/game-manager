package com.munstein.gamemanager.custom

import android.graphics.Rect
import android.view.View

class VerticalSpacing(private val marginInPx: Int = 0) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        outRect.top = marginInPx
        val lastItem = (parent.adapter?.itemCount ?: 0) - 1
        val isLastItem = parent.getChildAdapterPosition(view) == lastItem
        if (isLastItem) {
            outRect.bottom = marginInPx
        }
    }
}