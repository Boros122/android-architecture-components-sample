package com.boros.android.starter.shared.ui.recyclerView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class RecyclerViewAdapterBase<T : BaseCellModel, V : RecyclerView.ViewHolder> :
        RecyclerView.Adapter<V>() {

    protected var items: MutableList<T> = ArrayList()
    protected var itemClickListener: OnItemClickListener? = null

    fun autoNotify(
            newList: List<T>
    ) {
        val oldList = getCurrentItems()
        updateItems(newList)
        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].cellId == newList[newItemPosition].cellId
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size
            override fun getNewListSize() = newList.size
        })
        diff.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun add(item: T?) {
        item ?: return
        items.add(item)
    }

    fun add(position: Int, item: T?) {
        item ?: return
        items.add(position, item)
    }

    fun addAll(list: List<T>?) {
        list ?: return
        items.addAll(list)
    }

    fun set(position: Int, item: T?) {
        item ?: return
        items[position] = item
    }

    fun updateItems(list: List<T>?) {
        list ?: return
        items = ArrayList(list)
    }

    fun removeItem(position: Int) {
        if (position in 0 until items.size) {
            items.removeAt(position)
        }
    }

    fun removeItem(item: T?) {
        item ?: return
        val itemsPosition = items.indexOf(item)
        if (itemsPosition in 0 until items.size) {
            items.remove(item)
        }
    }

    fun clear() {
        items.clear()
    }

    fun getCurrentItems(): ArrayList<T> {
        return ArrayList(items)
    }

    fun getItem(index: Int): T {
        return items[index]
    }

    fun size(): Int {
        return items.size
    }
}