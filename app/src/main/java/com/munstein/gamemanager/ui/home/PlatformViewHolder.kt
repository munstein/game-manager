package com.munstein.gamemanager.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_platform.view.*

class PlatformViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val root by lazy { itemView.holder_platform_root }
    val title by lazy { itemView.holder_platform_txt_platform }

    interface OnHolderClick {
        fun onClick(platformTitle: String)
    }
}