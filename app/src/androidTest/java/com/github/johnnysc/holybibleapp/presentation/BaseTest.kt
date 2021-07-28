package com.github.johnnysc.holybibleapp.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RealmProvider
import com.github.johnnysc.holybibleapp.core.RecyclerViewMatcher
import com.github.johnnysc.holybibleapp.core.lazyActivityScenarioRule
import com.github.johnnysc.holybibleapp.presentation.languages.ChosenLanguage
import com.github.johnnysc.holybibleapp.presentation.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * @author Asatryan on 15.07.2021
 **/
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class BaseTest {
    @get:Rule
    val activityTestRule = lazyActivityScenarioRule<MainActivity>(launchActivity = false)

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var navigationPreferences: SharedPreferences
    private lateinit var languagesPreferences: SharedPreferences
    private lateinit var appContext: Context

    @Before
    open fun setup() {
        appContext = ApplicationProvider.getApplicationContext()
        sharedPreferences = sharedPreferences("MockCollapsedItemsIdList")
        navigationPreferences = sharedPreferences("mockNavigation")
        languagesPreferences = sharedPreferences("mockLanguagesFileName")
        sharedPreferences.clear()
        navigationPreferences.clear()
        languagesPreferences.clear()
        val realmProvider = RealmProvider.Base(appContext, object : ChosenLanguage {
            override fun isChosenEnglish() = true
            override fun isChosenRussian() = false
        })
        realmProvider.provide().use {
            it.executeTransaction {
                it.deleteAll()
            }
        }
        doBeforeActivityStart()
        activityTestRule.launch(Intent(appContext, MainActivity::class.java))
    }

    protected open fun doBeforeActivityStart() {}

    protected fun selectLanguage(english:Boolean) {
        languagesPreferences.edit().putInt("mockLanguagesKey", if (english) 0 else 1).apply()
    }

    protected fun startWithScreenId(id:Int) {
        navigationPreferences.edit().putInt("mockScreenId", id).apply()
    }

    private fun sharedPreferences(name: String) =
        appContext.getSharedPreferences(name, Context.MODE_PRIVATE)

    protected fun goBack() {
        uiDevice().pressBack()
    }

    protected fun checkAppNotShown(text: String = appName()) {
        uiDevice().findObject(UiSelector().text(text)).waitUntilGone(100L)
    }

    protected fun String.checkVisible() {
        onView(withText(this)).check(matches(isDisplayed()))
    }

    protected fun tap(position: Int) {
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(position)).perform(click())
    }

    protected fun Int.performTap() {
        onView(withId(this)).perform(click())
    }

    protected fun killAppAndReturn() {
        activityTestRule.relaunch(Intent(appContext, MainActivity::class.java))
    }

    private fun appName() = appContext.getString(appContext.applicationInfo.labelRes)

    private fun uiDevice() = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    protected fun checkItemText(position: Int, text: String) {
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(position)).check(
            matches(withText(text))
        )
    }

    protected fun String.checkDoesntExist() {
        onView(withText(this)).check(doesNotExist())
    }

    protected infix fun Int.isChecked(checked: Boolean) {
        if (checked)
            onView(withId(this)).check(matches(isChecked()))
        else
            onView(withId(this)).check(matches(isNotChecked()))
    }

    private fun SharedPreferences.clear() {
        edit().clear().apply()
    }
}