package com.munstein.gamemanager.ui.mygames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munstein.gamemanager.R
import com.munstein.gamemanager.ui.home.PlatformViewHolder

class GamesAdapter(private var items: List<String> = listOf(),
                   private val onRemoveClick: (String) -> Unit) : RecyclerView.Adapter<PlatformViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        return PlatformViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_platform, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PlatformViewHolder, position: Int) {
        val item = items[position]
        holder.delete.setOnClickListener { onRemoveClick.invoke(item) }
        holder.title.text = item
    }

    fun loadItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

}