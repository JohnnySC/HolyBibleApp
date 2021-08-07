package com.github.johnnysc.holybibleapp.presentation.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Asatryan on 12.07.2021
 **/
abstract class BaseAdapter<E, T : BaseViewHolder<E>> :
    RecyclerView.Adapter<T>(), ListMapper<E> {

    protected val list = ArrayList<E>()

    override fun map(data: List<E>) {
        val diffCallback = diffUtilCallback(list, data)
        val result = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(data)
        result.dispatchUpdatesTo(this)
    }

    abstract fun diffUtilCallback(list: ArrayList<E>, data: List<E>): DiffUtil.Callback

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: T, position: Int) =
        holder.bind(list[position])

    protected fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}

interface ClickListener<T> {
    fun click(item: T)
}