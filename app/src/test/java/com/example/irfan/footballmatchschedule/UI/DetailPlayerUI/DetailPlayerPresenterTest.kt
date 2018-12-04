package com.example.irfan.footballmatchschedule.UI.DetailPlayerUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.EventResponseMatches
import com.example.irfan.footballmatchschedule.Model.ResponseTeamPlayer
import com.example.irfan.footballmatchschedule.Model.TeamPlayer
import com.example.irfan.footballmatchschedule.R.string.teams
import com.example.irfan.footballmatchschedule.UI.LastMatchUI.LastMatchPresenter
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailPlayerPresenterTest {
    //digunakan untuk memastikan bahwa fungsi getPlayerDetail berhasil mendapatkan data team player dari API yang tersedia dengan id yang telah ditentukan

    @Mock
    lateinit var view: DetailPlayerView

    @Mock
    lateinit var presenter: DetailPlayerPresenter

    @Mock
    lateinit var apiRepository: ApiRepository

    @Mock
    lateinit var gson: Gson

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getPlayerDetail() {
        val players: MutableList<TeamPlayer> = mutableListOf()
        val response = ResponseTeamPlayer(players)
        val id = "133604"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayerDetail(id)).await(),
                ResponseTeamPlayer::class.java)
            ).thenReturn(response)
            presenter.getPlayerDetail(id)

            verify(view).showLoading()
            verify(view).showPlayerDetail(players)
            verify(view).hideLoading()
        }
    }
}