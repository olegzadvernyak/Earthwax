package com.earthwax

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wax_item.view.*

class WaxViewHolder(
    itemView: View,
    private val clickEventDelegate: ClickEventDelegate<Wax>
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Wax, isSelected: Boolean) {
        with(itemView) {
            setSelected(isSelected)

            titleTextView.text = item.title

            setOnClickListener {
                clickEventDelegate.onClick(item)
            }
            setOnLongClickListener {
                clickEventDelegate.onLongClick(item)
                return@setOnLongClickListener true
            }
        }
    }

}