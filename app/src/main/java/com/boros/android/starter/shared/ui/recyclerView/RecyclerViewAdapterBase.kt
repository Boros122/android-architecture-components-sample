package com.boros.android.starter.shared.ui.recyclerView

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class RecyclerViewAdapterBase<T, V : View> : RecyclerView.Adapter<ViewWrapper<V>>() {

    protected var items: MutableList<T> = ArrayList()

    private var callback: AdapterChanged? = null

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<V> {
        return ViewWrapper(onCreateItemView(parent, viewType))
    }

    protected abstract fun onCreateItemView(parent: ViewGroup, viewType: Int): V

    fun add(item: T?) {
        if (item != null) {
            items.add(item)
            onChange()
        }
    }

    operator fun set(position: Int, item: T) {
        items[position] = item
        onChange()
    }

    fun add(position: Int, item: T) {
        items.add(position, item)
        onChange()
    }

    fun setWithoutNotifying(position: Int, item: T) {
        items[position] = item
    }

    fun addAll(list: List<T>?) {
        if (list != null) {
            items.addAll(list)
            onChange()
        }
    }

    fun updateItems(list: ArrayList<T>?) {
        if (list != null) {
            items = ArrayList(list)
            onChange()
        }
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeItem(item: T?) {
        if (item != null) {
            val pos = items.indexOf(item)
            if (pos >= 0) {
                items.remove(item)
                notifyItemRemoved(pos)
            }
        }
    }

    fun clear() {
        items.clear()
        onChange()
    }

    fun getItem(index: Int): T {
        return items[index]
    }

    fun size(): Int {
        return items.size
    }

    fun setOnChangeListener(callback: AdapterChanged) {
        this.callback = callback
    }

    private fun onChange() {
        if (callback != null) {
            callback?.onChange()
        }
        //notifyDataSetChanged();
    }

    interface AdapterChanged {
        fun onChange()
    }
}