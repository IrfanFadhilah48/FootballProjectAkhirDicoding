package com.example.irfan.footballmatchschedule.UI.SearchTeamUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.ResponseEventMatchSearch
import com.example.irfan.footballmatchschedule.Model.ResponseTeam
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.UI.SearchMatchUI.SearchMatchPresenter
import com.example.irfan.footballmatchschedule.UI.SearchMatchUI.SearchMatchView
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

class SearchTeamPresenterTest {
    //digunakan untuk memastikan bahwa fungsi getAllTeams berhasil mendapatkan data team dari API yang tersedia yang sesuai dengan kalimat arsen

    @Mock
    lateinit var view: SearchTeamView

    @Mock
    lateinit var presenter: SearchTeamPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = SearchTeamPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getAllTeams() {
        val teams: MutableList<Team> = mutableListOf()
        val response = ResponseTeam(teams)
        val name = "Man U"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getSearchTeams(name)).await(),
                ResponseTeam::class.java)
            ).thenReturn(response)
            presenter.getAllTeams(name)

            verify(view).showLoading()
            verify(view).showAllTeams(teams)
            verify(view).hideLoading()
        }
    }
}