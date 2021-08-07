package com.github.johnnysc.holybibleapp.presentation.core.view

import android.content.Context
import android.util.AttributeSet
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.presentation.core.FavoriteMapper

/**
 * @author Asatryan on 12.07.2021
 **/
class FavoriteView : androidx.appcompat.widget.AppCompatImageView, FavoriteMapper {
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
        val icon = if (data) R.drawable.ic_star_full_black_24 else R.drawable.ic_star_empty_black_24
        setImageResource(icon)
    }
}