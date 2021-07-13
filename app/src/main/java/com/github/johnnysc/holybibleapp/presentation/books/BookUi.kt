package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.*

/**
 * @author Asatryan on 03.07.2021
 **/
sealed class BookUi : ComparableTextMapper<BookUi>, Matcher<Int>, Collapsing {

    override fun map(mapper: TextMapper) = Unit
    override fun matches(arg: Int) = false

    open fun changeState(): BookUi = Empty
    open fun saveId(cacheId: CollapsedIdsCache) = Unit
    open fun open(bookListener: BooksAdapter.BookListener) = Unit

    object Empty : BookUi()
    object Progress : BookUi()

    abstract class Info(
        protected open val id: Int,
        protected open val name: String
    ) : BookUi() {
        override fun map(mapper: TextMapper) = mapper.map(name)
        override fun matches(arg: Int) = arg == id
    }

    data class Base(override val id: Int, override val name: String) : Info(id, name) {
        override fun sameContent(item: BookUi) = if (item is Base) {
            name == item.name
        } else false

        override fun same(item: BookUi) = item is Base && id == item.id

        override fun open(bookListener: BooksAdapter.BookListener) = bookListener.showBook(id, name)
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {
        override fun collapseOrExpand(listener: BooksAdapter.CollapseListener) =
            listener.collapseOrExpand(id)

        override fun showCollapsed(mapper: CollapseMapper) = mapper.map(collapsed)
        override fun changeState() = Testament(id, name, !collapsed)
        override fun isCollapsed() = collapsed
        override fun sameContent(item: BookUi) = if (item is Testament) {
            name == item.name && collapsed == item.collapsed
        } else false

        override fun same(item: BookUi) = item is Testament && id == item.id

        override fun saveId(cacheId: CollapsedIdsCache) = cacheId.save(id)
    }

    data class Fail(
        private val message: String
    ) : BookUi() {
        override fun map(mapper: TextMapper) = mapper.map(message)
        override fun sameContent(item: BookUi) = if (item is Fail) {
            message == item.message
        } else false

        override fun same(item: BookUi) = sameContent(item)
    }
}

interface Collapsing {
    fun collapseOrExpand(listener: BooksAdapter.CollapseListener) = Unit
    fun showCollapsed(mapper: CollapseMapper) = Unit
    fun isCollapsed() = false
}