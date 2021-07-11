package com.github.johnnysc.holybibleapp.presentation

import com.github.johnnysc.holybibleapp.core.Abstract
import com.github.johnnysc.holybibleapp.core.Matcher

/**
 * @author Asatryan on 03.07.2021
 **/
sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper>, Matcher<Int>, Collapsing,
    Comparing {

    override fun map(mapper: StringMapper) = Unit
    override fun matches(arg: Int) = false

    open fun changeState(): BookUi = Empty
    open fun saveId(cacheId: IdCache) = Unit

    object Empty : BookUi()
    object Progress : BookUi()

    abstract class Info(
        protected open val id: Int, //todo use for getting chapters
        protected open val name: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(name)
        override fun matches(arg: Int) = arg == id
    }

    data class Base(override val id: Int, override val name: String) : Info(id, name) {
        override fun sameContent(bookUi: BookUi) = if (bookUi is Base) {
            name == bookUi.name
        } else false

        override fun same(bookUi: BookUi) = bookUi is Base && id == bookUi.id
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {
        override fun collapseOrExpand(listener: BibleAdapter.CollapseListener) =
            listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) = mapper.show(collapsed)
        override fun changeState() = Testament(id, name, !collapsed)
        override fun isCollapsed() = collapsed
        override fun sameContent(bookUi: BookUi) = if (bookUi is Testament) {
            name == bookUi.name && collapsed == bookUi.collapsed
        } else false

        override fun same(bookUi: BookUi) = bookUi is Testament && id == bookUi.id

        override fun saveId(cacheId: IdCache) = cacheId.save(id)
    }

    data class Fail(
        private val message: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) = mapper.map(message)
        override fun sameContent(bookUi: BookUi) = if (bookUi is Fail) {
            message == bookUi.message
        } else false

        override fun same(bookUi: BookUi) = sameContent(bookUi)
    }

    //todo improve later
    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }

    interface CollapseMapper : Abstract.Mapper {
        fun show(collapsed: Boolean)
    }
}

interface Collapsing {
    fun collapseOrExpand(listener: BibleAdapter.CollapseListener) = Unit
    fun showCollapsed(mapper: BookUi.CollapseMapper) = Unit
    fun isCollapsed() = false
}

interface Comparing {
    fun sameContent(bookUi: BookUi) = false
    fun same(bookUi: BookUi) = false
}