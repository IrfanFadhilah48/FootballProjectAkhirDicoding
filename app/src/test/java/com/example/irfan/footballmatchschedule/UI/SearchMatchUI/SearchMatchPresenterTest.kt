package com.example.irfan.footballmatchschedule.UI.SearchMatchUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.UI.LastMatchUI.LastMatchPresenter
import com.example.irfan.footballmatchschedule.UI.NextMatchUI.NextMatchPresenter
import com.example.irfan.footballmatchschedule.UI.NextMatchUI.NextMatchView
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest {
    //digunakan untuk memastikan bahwa fungsi getAll berhasil mendapatkan data match dari API yang tersedia yang sesuai dengan kalimat arsen

    @Mock
    lateinit var view: SearchMatchView

    @Mock
    lateinit var presenter: SearchMatchPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getAll() {
        val teams: MutableList<EventsMatches> = mutableListOf()
        val response = EventResponseMatches(teams)
        val name = "arsen"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchMatch(name)).await(),
                EventResponseMatches::class.java)
            ).thenReturn(response)
            presenter.getAll(name)
            verify(view).hideLoading()
            verify(view).showAllMatch(teams)
            verify(view).hideLoading()
        }
    }
}