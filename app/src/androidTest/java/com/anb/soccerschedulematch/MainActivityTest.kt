package com.anb.soccerschedulematch

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.anb.soccerschedulematch.feature.main.MainActivity
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testAppBehaviour(){

        // Digunakan Thread.sleep untuk menghindari program error karena data belum terambil

        Thread.sleep(5000)
        onView(ViewMatchers.withId(R.id.spinnerLeague))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.spinnerLeague)).perform(ViewActions.click())
        Thread.sleep(500)
        onView(ViewMatchers.withText("English Premier League"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("English Premier League")).perform(ViewActions.click())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.rv_list_league), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(AllOf.allOf(ViewMatchers.withId(R.id.rv_list_league), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click()))
        Thread.sleep(500)
        onView(ViewMatchers.withId(R.id.add_to_favorite)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(200)
        onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(500)
        onView(ViewMatchers.withText("Red Card")).perform(ViewActions.scrollTo())
        Thread.sleep(1000)
        pressBack()
        onView(ViewMatchers.withText("NEXT MATCH")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("NEXT MATCH")).perform(ViewActions.click())
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.rv_list_league), ViewMatchers.isDisplayed())).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("FAVORITE")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withText("FAVORITE")).perform(ViewActions.click())
        Thread.sleep(200)
        onView(AllOf.allOf(ViewMatchers.withId(R.id.srl_list_league), ViewMatchers.isDisplayed())).perform(ViewActions.swipeDown())
        onView(AllOf.allOf(ViewMatchers.withId(R.id.rv_list_league), ViewMatchers.isDisplayed())).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(500)
        onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())
        Thread.sleep(200)
        onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(200)
        pressBack()
        Thread.sleep(300)
        onView(AllOf.allOf(ViewMatchers.withText("LAST MATCH"),
                ViewMatchers.isDescendantOfA(ViewMatchers.withId(R.id.tabLayout)))).perform(ViewActions.click())
        Thread.sleep(3000)
    }


}