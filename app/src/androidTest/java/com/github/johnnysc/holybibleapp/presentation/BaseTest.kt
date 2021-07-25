package com.github.johnnysc.holybibleapp.presentation

import android.content.Context
import android.content.SharedPreferences
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.github.johnnysc.holybibleapp.R
import com.github.johnnysc.holybibleapp.core.RecyclerViewMatcher
import com.github.johnnysc.holybibleapp.presentation.main.MainActivity
import org.junit.Before
import org.junit.After
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * @author Asatryan on 15.07.2021
 **/
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
abstract class BaseTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var navigationPreferences: SharedPreferences
    private lateinit var languagesPreferences: SharedPreferences
    private lateinit var appContext: Context

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences = sharedPreferences("MockCollapsedItemsIdList")
        navigationPreferences = sharedPreferences("mockNavigation")
        languagesPreferences = sharedPreferences("mockLanguagesFileName")
        clear()
    }

    private fun sharedPreferences(name: String) =
        appContext.getSharedPreferences(name, Context.MODE_PRIVATE)

    @After
    fun clear() {
        sharedPreferences.clear()
        navigationPreferences.clear()
        languagesPreferences.clear()
    }

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

    /**
     * todo find a better method
     * Not doing well so far, use in debug mode with breakpoint at last line
     * Some interrupting screen when tap on app at desktop
     */
    protected fun killAppAndReturn() {
        with(uiDevice()) {
            pressRecentApps()
            findObject(UiSelector().textStartsWith("очистить")).click()
            findObject(UiSelector().text(appName())).click()
            pressRecentApps()
            findObject(UiSelector().text(appName())).click()
        }
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