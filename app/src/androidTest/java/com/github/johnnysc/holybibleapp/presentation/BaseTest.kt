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
    private lateinit var appContext: Context

    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        sharedPreferences =
            appContext.getSharedPreferences("MockCollapsedItemsIdList", Context.MODE_PRIVATE)
        clear()
    }

    @After
    fun clear() {
        sharedPreferences.edit().putStringSet("MockCollapsedItemsIdsKey", emptySet()).apply()
    }

    protected fun String.checkVisible() {
        onView(withText(this)).check(matches(isDisplayed()))
    }

    protected fun tap(position: Int) {
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(position)).perform(click())
    }

    /**
     * todo find a better method
     * Not doing well so far, use in debug mode with breakpoint at last line
     * Some interrupting screen when tap on app at desktop
     */
    protected fun killAppAndReturn() {
        val appName = appContext.getString(appContext.applicationInfo.labelRes)
        with(UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())) {
            pressRecentApps()
            findObject(UiSelector().textStartsWith("очистить")).click()
            findObject(UiSelector().text(appName)).click()
            pressRecentApps()
            findObject(UiSelector().text(appName)).click()
        }
    }

    protected fun checkItemText(position:Int, text:String) {
        onView(RecyclerViewMatcher(R.id.recyclerView).atPosition(position)).check(matches(withText(text)))
    }

    protected fun String.checkDoesntExist() {
        onView(withText(this)).check(doesNotExist())
    }
}