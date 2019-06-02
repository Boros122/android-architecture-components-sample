package com.boros.android.starter.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ViewWrapper<V : View>(val view: V) : RecyclerView.ViewHolder(view)