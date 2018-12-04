package com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseTeam
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.UI.DetailPlayerUI.DetailPlayerPresenter
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TeamDetailOverfiewPresenterTest {
    //digunakan untuk memastikan bahwa fungsi getOverview berhasil mendapatkan data detail team dari API yang tersedia yang seusuai dengan id team

    @Mock
    lateinit var view: TeamDetailOverfiewView

    @Mock
    lateinit var presenter: TeamDetailOverfiewPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailOverfiewPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getOverviewDetail() {
        val teamsDetail: MutableList<Team> = mutableListOf()
        val response = ResponseTeam(teamsDetail)
        val id = "133604"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamsDetail(id)).await(),
                ResponseTeam::class.java)
            ).thenReturn(response)

            presenter.getOverviewDetail(id)

            verify(view).showLoading()
            verify(view).showOverview(teamsDetail)
            verify(view).hideLoading()
        }
    }
}