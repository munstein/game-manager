package com.munstein.gamemanager.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munstein.gamemanager.R
import com.munstein.gamemanager.model.Platform

class PlatformsAdapter(private val platforms: Array<Platform>, private val onHolderClick: PlatformViewHolder.OnHolderClick) : RecyclerView.Adapter<PlatformViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        return PlatformViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_platform, parent, false))
    }

    override fun getItemCount(): Int {
        return platforms.size
    }

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        with(platforms[position]) {
            holder.title.text = title
            holder.root.setOnClickListener { onHolderClick.onClick(platformTitle = title) }
        }
    }
}