package com.github.johnnysc.holybibleapp.core

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.FrameLayout
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.presentation.books.BookUiMapper
import com.github.johnnysc.holybibleapp.presentation.chapters.ChapterUiMapper
import com.github.johnnysc.holybibleapp.presentation.verses.VerseUiMapper

/**
 * @author Asatryan on 12.07.2021
 **/
class CustomTextView : androidx.appcompat.widget.AppCompatTextView, TextMapper {
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

class CustomButton : androidx.appcompat.widget.AppCompatButton, TextMapper {
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

interface TextMapper : Abstract.Mapper.Data<String, Unit>, BookUiMapper<Unit>,
    ChapterUiMapper<Unit>, VerseUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) = map(name)
    override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) = map(text)
}

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
        val iconId: Int = if (data) {
            R.drawable.ic_expand_more_24
        } else {
            R.drawable.ic_expand_less_24
        }
        setImageResource(iconId)
    }
}

interface CollapseMapper : Abstract.Mapper.Data<Boolean, Unit>, BookUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
        map(isFavoriteOrCollapsed)
}

interface FavoriteMapper : Abstract.Mapper.Data<Boolean, Unit>, BookUiMapper<Unit>,
    ChapterUiMapper<Unit>, VerseUiMapper<Unit> {
    override fun map(id: Int, name: String, isFavoriteOrCollapsed: Boolean) =
        map(isFavoriteOrCollapsed)

    override fun map(visibleId: Int, id: Int, text: String, isFavorite: Boolean) = map(isFavorite)
}

class CustomFrameLayout : FrameLayout, FavoriteMapper {
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
        val colorId = if (data) R.color.gold else R.color.white
        val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(colorId, null)
        } else
            resources.getColor(colorId)
        setBackgroundColor(color)
    }
}

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