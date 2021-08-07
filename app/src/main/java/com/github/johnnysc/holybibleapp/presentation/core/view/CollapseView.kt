package com.github.johnnysc.holybibleapp.presentation.core.view

import android.content.Context
import android.util.AttributeSet
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.presentation.core.CollapseMapper

/**
 * @author Asatryan on 12.07.2021
 **/
class CollapseView : androidx.appcompat.widget.AppCompatImageView, CollapseMapper {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion
    override fun map(data: Boolean) {
        val iconId: Int = if (data) R.drawable.ic_expand_more_24 else R.drawable.ic_expand_less_24
        setImageResource(iconId)
    }
}