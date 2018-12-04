package com.example.irfan.footballmatchschedule.UI


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.example.irfan.footballmatchschedule.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.espresso.action.Press
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.Swipe
import android.support.test.espresso.action.GeneralSwipeAction
import android.support.test.espresso.assertion.ViewAssertions
import com.example.irfan.footballmatchschedule.Utils.EspressoIdlingResources
import org.junit.Before


@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    //1.  Menampilkan RecyclerView
    //2.  Memilih salah satu item pada RecyclerView pada posisi 1(ke-2)
    //3.  Klik drawable untuk menambahkan atau menghapus matchs ke dalam favorite(Anko Sqlite)
    //4.  Klik back ActionBar untuk masuk kembali ke activity/fragment sebelumnya
    //5.  Mengecek apakah kalimat Next Match pada fragment ke-2 tampil
    //6.  Klik fragment ke-2 dengan yaitu dengan nama Next Match
    //7.  Swipe kekiri untuk melihat fragment Next Match
    //8.  Menampilkan RecyclerView didalam Fragment Next Match
    //9.  Memilih salah satu item pada RecyclerView didalam fragment Next Match pada posisi 2(ke-3)
    //10. Klik drawable untuk menambahkan atau menghapus matchs ke dalam favorite(Anko Sqlite)
    //11. Klik back ActionBar untuk masuk kembali ke activity/fragment sebelumnya
    //12. Klik Salah satu item Bottom Navigation untuk masuk kedalam fragment Teams
    //13. Menampilkan RecyclerView didalam Fragment Teams
    //14. Memilih salah satu item pada RecyclerView didalam fragment Next Match pada posisi 6(ke-7)
    //15. Menampilkan tulisan PLAYERS pada tab_team di dalam Activity
    //16. Mengecek apakah RecyclerView pada fragment tab_team(PLAYERS) tampil
    //17. Memilih salah satu item pada RecyclerView didalam fragment Next Match pada posisi 0(ke-1)
    //18. Klik back ActionBar untuk masuk kembali ke activity/fragment sebelumnya
    //19. Klik drawable untuk menambahkan atau menghapus matchs ke dalam favorite(Anko Sqlite)
    //20. Klik salah satu item Bottom Navigatuin untuk masuk ke dalam faragment Favorite
    //21. Menampilkan tulisan Matches pada saat klik tab_favorite
    //22. Menampilkan tulisan Teams pada saat klik tab_favorite

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun homeActivityTest() {
        Thread.sleep(3000)

        onView(withId(R.id.recylerview)).check(ViewAssertions.matches(isDisplayed()))
        val recyclerView = onView(
            allOf(
                withId(R.id.recylerview),
                childAtPosition(
                    withId(R.id.constraintLayout),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(1000)

        val actionMenuItemView = onView(
            allOf(
                withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView.perform(click())

        Thread.sleep(1000)
        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigasi naik"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        Thread.sleep(1000)
        onView(withText("Next Match")).check(ViewAssertions.matches(isDisplayed()))
//        onView(withText("Next Match")).perform(click())

        val tabView = onView(
            allOf(
                withContentDescription("Next Match"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_matchs),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(click())

        val viewPager = onView(
            allOf(
                withId(R.id.viewpager_match),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.support.design.widget.CoordinatorLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager.perform(swipeLeft())

        Thread.sleep(2000)
        onView(withId(R.id.recylerviewNextMatch)).check(ViewAssertions.matches(isDisplayed()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.recylerviewNextMatch),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(1000)

        val actionMenuItemView2 = onView(
            allOf(
                withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView2.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Navigasi naik"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.nextMatch), withContentDescription("Team"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        Thread.sleep(3000)
        onView(withId(R.id.recylerviewTeam)).check(ViewAssertions.matches(isDisplayed()))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.recylerviewTeam),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(6, click()))

        val tabView2 = onView(
            allOf(
                withContentDescription("PLAYERS"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_team),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView2.perform(click())

        Thread.sleep(3000)
        val recyclerView4 = onView(
            allOf(
                withId(R.id.recyclerViewPlayerTeamDetail),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(1000)
        val appCompatImageButton3 = onView(
            allOf(
                withContentDescription("Navigasi naik"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton3.perform(click())

        Thread.sleep(1000)
        val actionMenuItemView3 = onView(
            allOf(
                withId(R.id.add_to_favorite), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.action_bar),
                        1
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        actionMenuItemView3.perform(click())

        val appCompatImageButton4 = onView(
            allOf(
                withContentDescription("Navigasi naik"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton4.perform(click())

        val bottomNavigationItemView2 = onView(
            allOf(
                withId(R.id.favorites), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView2.perform(click())

        val tabViewFav = onView(
            allOf(
                withContentDescription("Matches"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_favorite),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        tabViewFav.perform(click())

        val tabView3 = onView(
            allOf(
                withContentDescription("Teams"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_favorite),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView3.perform(click())

        val viewPager3 = onView(
            allOf(
                withId(R.id.viewpager_favorite),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.support.design.widget.CoordinatorLayout")),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        viewPager3.perform(swipeLeft())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun pagerSwipeLeft(): GeneralSwipeAction {
        return GeneralSwipeAction(
            Swipe.SLOW, GeneralLocation.CENTER_RIGHT,
            GeneralLocation.CENTER_LEFT, Press.FINGER
        )
    }
}
