package com.example.irfan.footballmatchschedule.UI.TeamDetailPlayerUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.ResponseTeamPlayer
import com.example.irfan.footballmatchschedule.Model.TeamPlayer
import com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI.TeamDetailOverfiewPresenter
import com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI.TeamDetailOverfiewView
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class TeamDetailPlayerPresenterTest {
    //digunakan untuk memastikan bahwa fungsi getPlayerTeam berhasil mendapatkan data player team dari API yang tersedia yang seusuai dengan id team

    @Mock
    lateinit var view: TeamDetailPlayerView

    @Mock
    lateinit var presenter: TeamDetailPlayerPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPlayerTeam() {
        val playerTeams: MutableList<TeamPlayer> = mutableListOf()
        val response = ResponseTeamPlayer(playerTeams)
        val id = "133604"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamPlayerDetail(id)).await(),
                ResponseTeamPlayer::class.java)
            ).thenReturn(response)

            presenter.getPlayerTeam(id)

            verify(view).showLoading()
            verify(view).showPlayerTeam(playerTeams)
            verify(view).hideLoading()
        }
    }
}