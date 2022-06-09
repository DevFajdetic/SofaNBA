package com.example.sofanba.ui.favorite.helpers

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPos: Int, toPos: Int): Boolean
    fun onItemDismiss(position: Int)
}