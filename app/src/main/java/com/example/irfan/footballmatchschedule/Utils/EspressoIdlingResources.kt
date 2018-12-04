package com.example.irfan.footballmatchschedule.Utils

import android.support.test.espresso.IdlingResource

object EspressoIdlingResources {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource =
        CountingIdling(RESOURCE)

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }
}