package com.munstein.gamemanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munstein.gamemanager.R
import com.munstein.gamemanager.model.Platform

class PlatformsAdapter(
    private val platforms: List<Platform>,
    private val onHolderClick: (platform: Platform) -> Unit,
    private val onRemoveHolderClick: (platform: Platform) -> Unit
) : RecyclerView.Adapter<PlatformViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        return PlatformViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_platform, parent, false))
    }

    override fun getItemCount(): Int {
        return platforms.size
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        with(platforms[position]) {
            holder.title.text = name
            holder.root.setOnClickListener { onHolderClick.invoke(this) }
            holder.delete.setOnClickListener { onRemoveHolderClick.invoke(this) }
        }
    }
}