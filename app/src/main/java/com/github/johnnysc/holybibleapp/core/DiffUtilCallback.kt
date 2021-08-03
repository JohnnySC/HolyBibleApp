package com.github.johnnysc.holybibleapp.core

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Asatryan on 04.07.2021
 **/
abstract class DiffUtilCallback<T, E : Same<T>>(
    private val oldList: List<T>,
    private val newList: List<T>,
    private val same: E,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    abstract fun same(item: T, same: E): Boolean

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        same.itemToCompare(newList[newItemPosition])
        return same(oldList[oldItemPosition], same)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        newList[newItemPosition]?.equals(oldList[oldItemPosition]) == true
}

interface Same<T> {

    fun itemToCompare(item: T)
}