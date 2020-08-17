package com.munstein.gamemanager.ui.mygames

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.munstein.gamemanager.R
import com.munstein.gamemanager.ui.common.GenericViewHolder

class GamesAdapter(
    private var items: List<String> = listOf(),
    private val onRemoveClick: (String) -> Unit
) : RecyclerView.Adapter<GenericViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.holder_generic, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        val item = items[position]
        holder.delete.setOnClickListener { onRemoveClick.invoke(item) }
        holder.title.text = item
    }
}