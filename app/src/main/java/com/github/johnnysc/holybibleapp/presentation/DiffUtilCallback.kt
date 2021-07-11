package com.github.johnnysc.holybibleapp.presentation

import androidx.recyclerview.widget.DiffUtil

/**
 * @author Asatryan on 04.07.2021
 **/
class DiffUtilCallback(
    private val oldList: List<BookUi>,
    private val newList: List<BookUi>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].sameContent(newList[newItemPosition])
}