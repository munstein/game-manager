package com.munstein.gamemanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munstein.gamemanager.R
import com.munstein.gamemanager.entity.Platform
import com.munstein.gamemanager.ui.common.GenericViewHolder

class PlatformsAdapter(
    private val platforms: List<Platform>,
    private val onHolderClick: (platform: Platform) -> Unit,
    private val onRemoveHolderClick: (platform: Platform) -> Unit
) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_generic, parent, false))
    }

    override fun getItemCount(): Int {
        return platforms.size
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        with(platforms[position]) {
            holder.title.text = name
            holder.root.setOnClickListener { onHolderClick.invoke(this) }
            holder.delete.setOnClickListener { onRemoveHolderClick.invoke(this) }
        }
    }
}