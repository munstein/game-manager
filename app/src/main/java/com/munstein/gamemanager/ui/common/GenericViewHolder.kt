package com.munstein.gamemanager.ui.common

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.holder_generic.view.*

class GenericViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val root by lazy { itemView.holder_generic_root }
    val title by lazy { itemView.holder_generic_txt_title }
    val delete by lazy { itemView.holder_generic_img_delete }
}