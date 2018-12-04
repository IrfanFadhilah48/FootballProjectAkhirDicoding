package com.example.irfan.footballmatchschedule.Utils

import com.example.irfan.footballmatchschedule.Model.MatchDetail
import com.example.irfan.footballmatchschedule.Utils.DateTimeConverter.longDate
import com.example.irfan.footballmatchschedule.Utils.DateTimeConverter.timeDate
import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat

private lateinit var matchDetail: MatchDetail
class DateTimeConverterTest{
    //digunakan untuk menkonversi format tanggal dari yyyy-MM-dd kedalam format tanggal EEE, dd MMM yyyy dan waktu HH:mmm

    @Test
    fun testToSimpleString(){
        assertEquals("Sel, 27 November 2018", longDate("2018-11-26 20:00:00"))
    }

    @Test
    fun testToHourString(){
        assertEquals("03:00", timeDate("2018-11-26 20:00:00"))
    }
}