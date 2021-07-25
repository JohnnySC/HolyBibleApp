package com.github.johnnysc.holybibleapp.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.holybibleapp.R

/**
 * @author Asatryan on 12.07.2021
 **/
abstract class BaseAdapter<E : ComparableTextMapper<E>, T : BaseViewHolder<E>> :
    RecyclerView.Adapter<T>() {

    protected val list = ArrayList<E>()

    fun update(new: List<E>) {
        val diffCallback = DiffUtilCallback(list, new)
        val result = DiffUtil.calculateDiff(diffCallback)
        list.clear()
        list.addAll(new)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: T, position: Int) =
        holder.bind(list[position])

    protected fun Int.makeView(parent: ViewGroup) =
        LayoutInflater.from(parent.context).inflate(this, parent, false)
}

interface ComparableTextMapper<T : ComparableTextMapper<T>> : Abstract.Object<Unit, TextMapper>,
    Comparing<T>

abstract class BaseViewHolder<E : ComparableTextMapper<E>>(view: View) :
    RecyclerView.ViewHolder(view) {
    open fun bind(item: E) {
    }

    class FullscreenProgress<E : ComparableTextMapper<E>>(view: View) :
        BaseViewHolder<E>(view) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        override fun bind(item: E) {
            val anim: Animation = CircleAnimation(imageView, 100f)
            anim.duration = 3000
            anim.repeatMode = Animation.RESTART
            anim.repeatCount = Int.MAX_VALUE
            anim.interpolator = AccelerateDecelerateInterpolator()
            imageView.startAnimation(anim)
        }
    }

    class Fail<E : ComparableTextMapper<E>>(
        view: View,
        private val retry: Retry
    ) : BaseViewHolder<E>(view) {
        private val message = itemView.findViewById<CustomTextView>(R.id.messageTextView)
        private val button = itemView.findViewById<View>(R.id.tryAgainButton)
        override fun bind(item: E) {
            item.map(message)
            button.setOnClickListener {
                retry.tryAgain()
            }
        }
    }
}

interface ClickListener<T> {
    fun click(item: T)
}