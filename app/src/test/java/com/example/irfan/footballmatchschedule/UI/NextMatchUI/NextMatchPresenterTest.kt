package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest{
    //digunakan untuk memastikan bahwa fungsi getNextMatch berhasil mendapatkan data match dari API yang tersedia

    @Mock
    lateinit var view: NextMatchView

    @Mock
    lateinit var presenter: NextMatchPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<EventsMatches> = mutableListOf()
        val response = EventResponseMatches(teams)
        val id = "4328"

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getNextLeagueTeams(id)).await(),
                    EventResponseMatches::class.java
                )
            ).thenReturn(response)

            presenter.getEventNext(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showEventList(teams)
            Mockito.verify(view).hideLoading()
        }
    }

    @Test
    fun getLeagueSpinner(){
        val league: MutableList<LeagueItems> = mutableListOf()
        val response = ResponseLeague(league)

        GlobalScope.launch {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository.doRequest(TheSportDBApi.getAllLeagues()).await(),
                    ResponseLeague::class.java
                )
            ).thenReturn(response)
            presenter.getLeagueList()

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showListLeague(league)
            Mockito.verify(view).hideLoading()
        }
    }
}