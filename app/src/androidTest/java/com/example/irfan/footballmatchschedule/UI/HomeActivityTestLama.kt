package com.example.irfan.footballmatchschedule.UI

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.R.id.*
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTestLama{
    @Rule
    @JvmField var test = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun init(){
        test.activity.supportFragmentManager.beginTransaction()

    }

    @Test
    fun instrumentTest(){
        //1.menampilkan sebuah progress Bar
        //2.menampilkan sebuah recyclerview LastMatchFragment
        //3.scroll recyclerview sampai ke posisi ke-8
        //4.memilih item recyclerview di posisi 8
        //5.digunakan untuk melihat apakah button favorite tampil atau tidak
        //6.digunakan untuk klik menambahkan item ke favorite
        //7.digunakan untuk apakah drawable imageview apakah sudah benar menampilkan drawable added_to_facorite jika item sudah ditambahkan ke sqlite
        //8.kembali ke dalam home Activity yaitu Fragment LastMatch
        //9.mengecek apakah bottom_navigation(BottomNavigationView) sudah ditampilkan
        //10.Memberikan action click pada salah satu menu bottom navigation yaitu favorites
        //11.Digunakan mengecek apakah recyclerview favorites sudah tampil atau belum
        //12.Digunakan untuk Memilih item posisi ke 1 pada recyclerview
        //13.digunakan untuk klik menghapus item ke favorite
        //14.digunakan untuk apakah drawable imageview apakah sudah benar menampilkan drawable add_to_facorite jika data belum ada di sqlite
        //15.digunakan untuk kembali

        onView(ViewMatchers.withId(spinnerLastMatch)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(progressbar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(3000)
        onView(ViewMatchers.withId(recylerview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Thread.sleep(1000)
        onView(ViewMatchers.withId(recylerview))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        onView(ViewMatchers.withId(recylerview)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))

        Thread.sleep(1000)
        onView(withId(add_to_favorite)).check(matches(isDisplayed()))

        Thread.sleep(1000)
        onView(withId(add_to_favorite)).perform(click())

        Thread.sleep(1000)
        withTagValue(CoreMatchers.equalTo(R.drawable.ic_added_to_favorites))

        Thread.sleep(1000)
        Espresso.pressBack()

        Thread.sleep(1000)
        onView(withId(bottom_navigation)).check(matches(isDisplayed()))

        Thread.sleep(1000)
        onView(withId(favorites)).perform(click())

        Thread.sleep(1000)
        onView(withId(recylerviewFavorite)).check(matches(isDisplayed()))

        Thread.sleep(1000)
        onView(withId(recylerviewFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Thread.sleep(1000)
        onView(withId(add_to_favorite)).perform(click())

        Thread.sleep(1000)
        withTagValue(CoreMatchers.equalTo(R.drawable.ic_add_to_favorites))

        Thread.sleep(1000)
        Espresso.pressBack()

    }




}