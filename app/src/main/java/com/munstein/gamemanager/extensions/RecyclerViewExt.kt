package com.munstein.gamemanager.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.munstein.gamemanager.R
import com.munstein.gamemanager.custom.HorizontalMarginDecoration
import com.munstein.gamemanager.custom.VerticalMarginDecorator

fun RecyclerView.setLinearLayoutManagerAndMargins() {
    val recyclerMargin = resources.getDimension(R.dimen.app_margin).toInt()
    this.layoutManager = LinearLayoutManager(context)
    this.addItemDecoration(HorizontalMarginDecoration(recyclerMargin))
    this.addItemDecoration(VerticalMarginDecorator(recyclerMargin))
}