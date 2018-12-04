package com.example.irfan.footballmatchschedule.UI.Team

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.Model.ResponseTeam
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.UI.SearchMatchUI.SearchMatchPresenter
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TeamPresenterTest{
    //digunakan untuk memastikan bahwa fungsi getAllTeams berhasil mendapatkan data team dari API yang tersedia yang ada pada premier league

    @Mock
    lateinit var view: TeamView

    @Mock
    lateinit var presenter: TeamPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeam(){
        val teams: MutableList<Team> = mutableListOf()
        val response = ResponseTeam(teams)
        val name = "English Premier League"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllTeams(name)).await(),
                ResponseTeam::class.java)
            ).thenReturn(response)
            presenter.getTeam(name)

            verify(view).showLoading()
            verify(view).showTeamList(teams)
            verify(view).hideLoading()
        }
    }

    @Test
    fun getLeagueSpinner(){
        val league: MutableList<LeagueItems> = mutableListOf()
        val response = ResponseLeague(league)

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getAllLeagues()).await(),
                ResponseLeague::class.java)
            ).thenReturn(response)
            presenter.showLeagueList()

            verify(view).showLoading()
            verify(view).showListLeague(league)
            verify(view).hideLoading()
        }
    }
}