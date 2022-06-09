package com.example.sofanba.ui.favorite.helpers

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: ItemTouchHelperAdapter):
    ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            return makeMovementFlags(dragFlags, 0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        if(viewHolder.itemViewType != target.itemViewType) {
            return false
        } else adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //NOT A FEATURE
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        viewHolder.itemView.alpha = ALPHA_FULL
        if(viewHolder is ItemTouchHelperViewHolder) {
            val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemClear()
        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if(viewHolder is ItemTouchHelperViewHolder) {
                val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
                itemViewHolder.onItemSelected()
            }
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    companion object {
        const val ALPHA_FULL=1.0f
    }
}