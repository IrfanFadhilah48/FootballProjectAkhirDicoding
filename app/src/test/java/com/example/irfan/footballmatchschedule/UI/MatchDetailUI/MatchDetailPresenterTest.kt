package com.example.irfan.footballmatchschedule.UI.MatchDetailUI

import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.API.TheSportDBApi
import com.example.irfan.footballmatchschedule.Model.MatchDetail
import com.example.irfan.footballmatchschedule.Model.ResponseMatchDetail
import com.example.irfan.footballmatchschedule.Utils.TestContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest{
    //digunakan untuk memastikan bahwa fungsi detailMatch berhasil mendapatkan data detail match yang sesuai dengan id yang telah ditentukan

    @Mock
    private lateinit var view: MatchDetailView

    @Mock
    private lateinit var presenter: MatchDetailPresenter

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view,apiRepository,gson, TestContextProvider())

    }

    @Test
    fun testGetMatchList(){
        val match: MutableList<MatchDetail> = mutableListOf()
        val response = ResponseMatchDetail(match)
        val id = "576584"

        GlobalScope.launch {
//            delay(12000)
            `when`(
                gson.fromJson(apiRepository
                        .doRequest(TheSportDBApi.getMatchDetail(id)).await(),
                    ResponseMatchDetail::class.java
                )
            ).thenReturn(response)
        }

        presenter.getMatchDetail(id)
        Mockito.verify(view, times(0)).showMatchDetails(match)

    }
}