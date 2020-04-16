package com.boros.android.sample.features.main.settings

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.boros.android.sample.R
import com.boros.android.sample.shared.ui.view.BaseView
import com.boros.android.sample.shared.util.extensions.gone
import com.boros.android.sample.shared.util.extensions.visible
import kotlinx.android.synthetic.main.view_settings_checkbox_item.view.*

class SettingsCheckboxItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : BaseView(context, attrs, defStyle) {

    private val typedArray: TypedArray by lazy { context.theme.obtainStyledAttributes(attrs, R.styleable.SettingsCheckboxItem, defStyle, 0) }

    var titleText: String? = null
        set(value) {
            field = value
            if (value != null && value.isNotEmpty()) {
                titleTextView?.visible()
                titleTextView?.text = value
            } else {
                titleTextView?.gone()
            }
        }

    init {
        View.inflate(getContext(), R.layout.view_settings_checkbox_item, this)

        titleText = typedArray.getString(R.styleable.SettingsCheckboxItem_titleText)
    }

    fun addCheckChangedListener(listener: CheckChangedListener) {
        checkBox?.setOnCheckedChangeListener { _, isChecked ->
            listener.onCheckChanged(isChecked)
        }
    }

    interface CheckChangedListener {
        fun onCheckChanged(isChecked: Boolean)
    }

}