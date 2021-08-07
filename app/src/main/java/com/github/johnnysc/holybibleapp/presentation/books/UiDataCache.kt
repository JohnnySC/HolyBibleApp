package com.github.johnnysc.holybibleapp.presentation.books

import com.github.johnnysc.holybibleapp.core.ChangeFavorite

/**
 * @author Asatryan on 04.07.2021
 **/
interface UiDataCache : ChangeFavorite<Int> {

    fun cache(list: List<BookUi>): ArrayList<BookUi>
    fun changeState(item: BookUi): ArrayList<BookUi>
    fun saveState()

    class Base(private val cacheId: CollapsedIdsCache) : UiDataCache {
        private val cachedList = ArrayList<BookUi>()

        override fun cache(list: List<BookUi>): ArrayList<BookUi> {
            cachedList.clear()
            cachedList.addAll(list)
            var newList: ArrayList<BookUi> = ArrayList(list)
            val ids = cacheId.read()
            ids.forEach { id ->
                cachedList.find { it.map(BookUiMapper.Id(id)) }?.let { newList = changeState(it) }
            }
            return newList
        }

        override fun changeState(item: BookUi): ArrayList<BookUi> {
            val newList = ArrayList<BookUi>()

            var add = false
            cachedList.forEachIndexed { index, book ->
                if (book is BookUi.Testament) {
                    if (item == book) {
                        val element = book.map(BookUiMapper.ChangeTestamentState())
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.map(BookUiMapper.CollapsedState())
                    } else {
                        newList.add(book)
                        add = !book.map(BookUiMapper.CollapsedState())
                    }
                } else {
                    if (add) newList.add(book)
                }
            }

            return newList
        }

        override fun saveState() {
            cacheId.start()
            cachedList.filter { it.map(BookUiMapper.CollapsedState()) }
                .forEach { it.map(BookUiMapper.Store(cacheId)) }
            cacheId.finish()
        }

        override fun changeFavorite(id: Int) {
            val itemToChange = cachedList.find { it.map(BookUiMapper.Id(id)) } ?: BookUi.Empty
            val newItem = itemToChange.map(BookUiMapper.ChangeBookState())
            cachedList[cachedList.indexOf(itemToChange)] = newItem
        }
    }
}