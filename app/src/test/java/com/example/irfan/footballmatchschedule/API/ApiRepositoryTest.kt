package com.example.irfan.footballmatchschedule.API

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest{
    //digunakan untuk memastikan bahwa fungsi doRequest bisa berjalan baik

    @Test
    fun testDoRequest(){
        val apiRepository = mock(ApiRepository::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League"
        apiRepository.doRequest(url)
        verify(apiRepository).doRequest(url)
    }
}