package com.earthwax

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class WaxAdapter : RecyclerView.Adapter<WaxViewHolder>() {

    private var items = listOf<Wax>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaxViewHolder =
        WaxViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wax_item, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WaxViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setWaxes(waxes: List<Wax>) {
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition].id == waxes[newItemPosition].id

            override fun getOldListSize(): Int = items.size

            override fun getNewListSize(): Int = waxes.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                items[oldItemPosition].title == waxes[newItemPosition].title
        })
        items = waxes
        diff.dispatchUpdatesTo(this)
    }

}