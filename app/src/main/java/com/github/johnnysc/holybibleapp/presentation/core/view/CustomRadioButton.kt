package com.github.johnnysc.holybibleapp.presentation.core.view

import android.content.Context
import android.util.AttributeSet
import com.github.johnnysc.holybibleapp.presentation.core.TextMapper

/**
 * @author Asatryan on 12.07.2021
 **/
class CustomRadioButton : androidx.appcompat.widget.AppCompatRadioButton, TextMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    override fun map(data: String) {
        text = data
    }
}