package com.earthwax

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class WaxAdapter(private val context: Context) : RecyclerView.Adapter<WaxViewHolder>() {

    private var items = listOf<Wax>()
    private var selectedItems = mutableSetOf<Wax>()

    private var isInSelection = false
    private var actionModeCallback: ActionMode.Callback? = null
    private var selectionCountListener: ((Int) -> Unit)? = null

    private val clickEventDelegate: ClickEventDelegate<Wax> = object : ClickEventDelegate<Wax> {
        override fun onClick(item: Wax) {
            if (isInSelection) {
                toggleItemSelection(item)
            }
        }

        override fun onLongClick(item: Wax) {
            if (!isInSelection) {
                actionModeCallback?.let { callback ->
                    (context as AppCompatActivity).startSupportActionMode(callback)
                    isInSelection = true
                    toggleItemSelection(item)
                }
            }
        }
    }

    private fun toggleItemSelection(item: Wax) {
        if(selectedItems.contains(item)){
            selectedItems.remove(item)
        } else {
            selectedItems.add(item)
        }
        notifyItemChanged(items.indexOf(item))
        selectionCountListener?.invoke(selectedItems.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaxViewHolder =
        WaxViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.wax_item, parent, false), clickEventDelegate)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WaxViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, selectedItems.contains(item))
    }

    fun setWaxes(waxes: List<Wax>) {
        selectedItems = selectedItems.filter { wax -> waxes.contains(wax) }.toMutableSet()
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

    fun enableActionMode(actionModeCallback: ActionMode.Callback, selectionCountListener: (Int) -> Unit) {
        this.actionModeCallback = actionModeCallback
        this.selectionCountListener = selectionCountListener
    }

    fun setSelection(waxes: List<Wax>) {
        if (!waxes.isEmpty()) {
            actionModeCallback?.let<ActionMode.Callback, Unit> { callback ->
                (context as AppCompatActivity).startSupportActionMode(callback)
                isInSelection = true
                selectedItems.addAll(waxes)
                selectionCountListener?.invoke(selectedItems.size)
            }
        }
    }

    fun endSelection() {
        isInSelection = false
        val selectedItemsCache = selectedItems.toSet()
        selectedItems.clear()
        selectedItemsCache.forEach { selectedItem ->
            notifyItemChanged(items.indexOf(selectedItem))
        }
    }

    fun getSelectedItems(): List<Wax> = selectedItems.toList()

}