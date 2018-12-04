package com.example.irfan.footballmatchschedule.UI


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions
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

@LargeTest
@RunWith(AndroidJUnit4::class)
class SearchActivityTest {

    //1.  Klik ActionBar SearchView untuk masuk ke dalam Activity Search
    //2.  Klik ActionBar SearchView untuk mencari Teams atau Matchs
    //3.  Mencoba untuk memasukkan huruf arsen kedalam SearchView
    //4.  Mencoba untuk mencari Matchs atau Teams yang memiliki huruf arsen
    //5.  Memilih salah satu item RecyclerView dalam fragment Matches pada saat posisi 0(ke-1)
    //6.  Klik drawable untuk menambahkan atau menghapus matchs ke dalam favorite(Anko Sqlite)
    //7.  Klik back ActionBar untuk masuk kembali ke activity/fragment sebelumnya
    //8.  Klik Fragment ke-2 pada Activity Search dan menampilkan title fragment dengan nama Teams
    //9.  Mengecek apakah RcyclerView dalam Fragment Teams tampil atau tidak
    //10. Memilih salah satu item RecyclerView dalam fragment Matches pada saat posisi 0(ke-1)
    //11. Klik back ActionBar untuk masuk kembali ke activity/fragment sebelumnya
    //12. Memilih salah satu item RecyclerView dalam fragment Matches pada saat posisi 2(ke-3)
    //13. Klik Fragment ke-2 dan menampilkan title fragment dengan nama PLAYERS
    //14. Klik ActionBar SearchView untuk mencari Teams atau Matchs

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun searchActivityTest() {
        val actionMenuItemView = onView(
            allOf(
                withId(R.id.search), withContentDescription("Search"),
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

        val actionMenuItemView2 = onView(
            allOf(
                withId(R.id.searchActivity), withContentDescription("Search"),
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

        val searchAutoComplete = onView(
            allOf(
                withId(R.id.search_src_text),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete.perform(replaceText("arsen"), closeSoftKeyboard())

        val searchAutoComplete2 = onView(
            allOf(
                withId(R.id.search_src_text), withText("arsen"),
                childAtPosition(
                    allOf(
                        withId(R.id.search_plate),
                        childAtPosition(
                            withId(R.id.search_edit_frame),
                            1
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        searchAutoComplete2.perform(pressImeActionButton())

        Thread.sleep(3000)
        val recyclerView = onView(
            allOf(
                withId(R.id.recyclerViewMatchSearch),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
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

        val actionMenuItemView4 = onView(
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
        actionMenuItemView4.perform(click())

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

        val tabView = onView(
            allOf(
                withContentDescription("Teams"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_search),
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
                withId(R.id.viewpager_search),
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

        onView(withId(R.id.recyclerViewTeamSearch)).check(ViewAssertions.matches(isDisplayed()))
        val recyclerView2 = onView(
            allOf(
                withId(R.id.recyclerViewTeamSearch),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val actionMenuItemView5 = onView(
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
        actionMenuItemView5.perform(click())

        val actionMenuItemView6 = onView(
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
        actionMenuItemView6.perform(click())

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
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val recyclerView3 = onView(
            allOf(
                withId(R.id.recyclerViewTeamSearch),
                childAtPosition(
                    withClassName(`is`("android.widget.RelativeLayout")),
                    0
                )
            )
        )
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

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

//        Thread.sleep(3000)
//        val viewPager2 = onView(
//            allOf(
//                withId(R.id.viewpager_team),
//                childAtPosition(
//                    allOf(
//                        withId(R.id.main_content),
//                        childAtPosition(
//                            withId(android.R.id.content),
//                            0
//                        )
//                    ),
//                    1
//                ),
//                isDisplayed()
//            )
//        )
//        viewPager2.perform(swipeLeft())

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
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageButton3.perform(click())
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
}
