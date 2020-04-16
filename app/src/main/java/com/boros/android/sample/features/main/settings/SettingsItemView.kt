package com.boros.android.sample.features.main.settings

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import com.boros.android.sample.R
import com.boros.android.sample.shared.ui.view.BaseView
import com.boros.android.sample.shared.util.extensions.gone
import com.boros.android.sample.shared.util.extensions.visible
import kotlinx.android.synthetic.main.view_settings_item.view.*

class SettingsItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : BaseView(context, attrs, defStyle) {

    private val typedArray: TypedArray by lazy { context.theme.obtainStyledAttributes(attrs, R.styleable.SettingsItem, defStyle, 0) }

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

    var subTitleText: String? = null
        set(value) {
            field = value
            if (value != null && value.isNotEmpty()) {
                subTitleTextView?.visible()
                subTitleTextView?.text = value
            } else {
                subTitleTextView?.gone()
            }
        }

    var settingsIcon: Int? = null
        set(value) {
            field = value
            if (value != null && value != 0) {
                iconImageView?.visible()
                iconImageView?.setImageResource(value)
            } else {
                iconImageView?.gone()
            }
        }

    init {
        View.inflate(getContext(), R.layout.view_settings_item, this)

        titleText = typedArray.getString(R.styleable.SettingsItem_titleText)
        subTitleText = typedArray.getString(R.styleable.SettingsItem_subTitleText)
        settingsIcon = typedArray.getResourceId(R.styleable.SettingsItem_settingsIcon, 0)
    }

}