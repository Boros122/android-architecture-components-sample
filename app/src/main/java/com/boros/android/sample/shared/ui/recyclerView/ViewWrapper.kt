package com.boros.android.sample.shared.ui.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewWrapper<V : View>(val view: V) : RecyclerView.ViewHolder(view)