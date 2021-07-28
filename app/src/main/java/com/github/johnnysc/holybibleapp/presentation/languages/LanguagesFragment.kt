package com.github.johnnysc.holybibleapp.presentation.languages

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.CustomRadioButton
import com.github.johnnysc.holybibleapp.presentation.main.BaseFragment

/**
 * @author Asatryan on 18.07.2021
 **/
class LanguagesFragment : BaseFragment<LanguagesViewModel>() {

    override fun showBackIcon() = viewModel.showBackIcon()
    override fun layoutResId() = R.layout.fragment_languages
    override fun viewModelClass() = LanguagesViewModel::class.java

    private lateinit var englishFlagView: ImageView
    private lateinit var russianFlagView: ImageView
    private lateinit var englishRadioButton: CustomRadioButton
    private lateinit var russianRadioButton: CustomRadioButton

    private lateinit var handler: Handler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handler = Handler(Looper.getMainLooper())
        //todo viewBinding
        englishRadioButton = view.findViewById(R.id.englishRadioButton)
        russianRadioButton = view.findViewById(R.id.russianRadioButton)
        val english = view.findViewById<View>(R.id.english)
        val russian = view.findViewById<View>(R.id.russian)
        englishFlagView = view.findViewById(R.id.engImageView)
        russianFlagView = view.findViewById(R.id.imageViewRu)

        val chooseEnglish: (v: View) -> Unit = {
            viewModel.chooseEnglish()
        }
        english.setOnClickListener(chooseEnglish)
        englishRadioButton.setOnClickListener(chooseEnglish)

        val chooseRussian: (v: View) -> Unit = {
            viewModel.chooseRussian()
        }
        russian.setOnClickListener(chooseRussian)
        russianRadioButton.setOnClickListener(chooseRussian)

        viewModel.observeLanguage(this, {
            it.show(
                englishRadioButton,
                russianRadioButton,
                ::russianChosen,
                ::englishChosen,
                ::noLanguageChosen
            )
        })
        viewModel.init()
    }

    private fun englishChosen() {
        setStaticRussianFlag()
        englishRadioButton.isChecked = true
        val animatedVector =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.english_animated_flag)
        englishFlagView.setImageDrawable(animatedVector)
        animatedVector?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable) {
                handler.post { animatedVector.start() }
            }
        })
        animatedVector?.start()
    }

    private fun russianChosen() {
        setStaticEnglishFlag()
        russianRadioButton.isChecked = true
        val animatedVector =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.russian_animated_flag)
        russianFlagView.setImageDrawable(animatedVector)
        animatedVector?.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable) {
                handler.post { animatedVector.start() }
            }
        })
        animatedVector?.start()
    }

    private fun noLanguageChosen() {
        setStaticRussianFlag()
        setStaticEnglishFlag()
    }

    private fun setStaticRussianFlag() = russianFlagView.setImageResource(R.drawable.russian_flag)
    private fun setStaticEnglishFlag() = englishFlagView.setImageResource(R.drawable.english_flag)
}