package com.github.johnnysc.holybibleapp.presentation.core

import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.Retry
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomFrameLayout
import com.github.johnnysc.holybibleapp.presentation.core.view.CustomTextView
import com.github.johnnysc.holybibleapp.presentation.core.view.FavoriteView

/**
 * @author Asatryan on 12.07.2021
 **/
abstract class BaseViewHolder<E>(view: View) :
    RecyclerView.ViewHolder(view) {
    open fun bind(item: E) {}

    abstract class Base<E>(view: View, private val clickListener: ClickListener<E>) :
        BaseViewHolder<E>(view) {

        private val reveal: SwipeMenuLayout = itemView.findViewById(R.id.swipeRevealLayout)
        private val backgroundView: CustomFrameLayout = itemView.findViewById(R.id.backgroundView)
        private val favoriteButton: FavoriteView = itemView.findViewById(R.id.changeFavoriteView)
        private val favoriteLayout: View = itemView.findViewById(R.id.changeFavoriteLayout)
        private val textView: CustomTextView = itemView.findViewById(R.id.textView)

        override fun bind(item: E) {
            map(item, backgroundView, favoriteButton, textView)
            clickableView().setOnClickListener { clickListener.click(item) }
            favoriteLayout.setOnClickListener {
                mapFavorite(item)
                reveal.smoothClose()
            }
        }

        protected open fun clickableView(): View = textView

        protected abstract fun map(
            item: E,
            background: CustomFrameLayout,
            button: FavoriteView,
            text: CustomTextView,
        )

        protected abstract fun mapFavorite(item: E)
    }


    class FullscreenProgress<E>(view: View) :
        BaseViewHolder<E>(view) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        override fun bind(item: E) {
            val anim: Animation = CircleAnimation(imageView, 100f)
            anim.duration = 3000
            anim.repeatMode = Animation.RESTART
            anim.repeatCount = Int.MAX_VALUE
            anim.interpolator = AccelerateDecelerateInterpolator()
            imageView.startAnimation(anim)
        }
    }

    abstract class Fail<E>(view: View, private val retry: Retry) : BaseViewHolder<E>(view) {
        private val message: CustomTextView = itemView.findViewById(R.id.messageTextView)
        private val button: View = itemView.findViewById(R.id.tryAgainButton)
        override fun bind(item: E) {
            mapErrorMessage(item, message)
            button.setOnClickListener { retry.tryAgain() }
        }

        abstract fun mapErrorMessage(item: E, textMapper: TextMapper)
    }
}