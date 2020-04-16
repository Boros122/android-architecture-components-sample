package com.boros.android.sample.shared.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.boros.android.sample.R
import com.boros.android.sample.shared.enums.ExampleEnum

class ExampleView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null, defaultStyle: Int = 0) : BaseView(context, attributeSet, defaultStyle) {

    init {
        View.inflate(context, R.layout.view_example, this)
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExampleView)
        val exampleDrawable = attributes.getDrawable(R.styleable.ExampleView_exampleReference)
        val exampleString = attributes.getString(R.styleable.ExampleView_exampleString)
        val exampleBoolean = attributes.getBoolean(R.styleable.ExampleView_exampleBoolean, false)
        val exampleInteger = attributes.getResourceId(R.styleable.ExampleView_exampleInteger, 0)
        val exampleColor = attributes.getColor(R.styleable.ExampleView_exampleColor, 0)
        val exampleEnumValue = attributes.getInt(R.styleable.ExampleView_exampleEnum, 0)
        val exampleEnum = ExampleEnum.fromInt(exampleEnumValue)
        attributes.recycle()
    }

}