package com.boros.android.starter.shared.ui.dialog.optionSelector

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boros.android.starter.R
import com.boros.android.starter.shared.ui.recyclerView.BaseCellModel
import com.boros.android.starter.shared.ui.view.BaseView
import com.boros.android.starter.shared.util.extensions.gone
import com.boros.android.starter.shared.util.extensions.visible
import kotlinx.android.synthetic.main.view_option_selector_dialog.view.*

class OptionSelectorDialogView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : BaseView(context, attrs, defStyle) {

    init {
        View.inflate(context, R.layout.view_option_selector_dialog, this)
    }

    fun bind(model: OptionSelectorDialogModel, dismiss: () -> Unit = {}) {
        if (model.title != null) {
            titleTextView?.visible()
            titleTextView?.text = model.title
        } else {
            titleTextView?.gone()
        }

        recyclerView?.adapter = OptionSelectorDialogRecyclerViewAdapter(model.itemModels, dismiss, model.listener)
        recyclerView?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    companion object {
        data class OptionSelectorDialogModel(val title: String? = null, val itemModels: ArrayList<ItemModel>, val listener: OptionSelectedListener)
        data class ItemModel(override val cellId: String, val id: Int, val title: String) : BaseCellModel
        interface OptionSelectedListener {
            fun onOptionSelected(id: Int)
        }
    }

}