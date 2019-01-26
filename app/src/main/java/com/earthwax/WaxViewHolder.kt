package com.earthwax

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wax_item.view.*

class WaxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Wax) {
        with(itemView) {
            titleTextView.text = item.title
        }
    }

}