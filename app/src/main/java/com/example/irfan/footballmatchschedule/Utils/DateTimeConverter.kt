package com.example.irfan.footballmatchschedule.Utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeConverter{

    fun formatDate(date : String, format : String) : String{
        var convert = ""
        val old = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        old.timeZone = TimeZone.getTimeZone("GMT")
        val parse = old.parse(date)

        val newFormat = SimpleDateFormat(format)
        newFormat.timeZone = TimeZone.getTimeZone("Asia/Bangkok")
        convert = newFormat.format(parse)

        return convert
    }

    fun longDate(date: String) : String{
        return formatDate(date, "EEE, dd MMMM yyyy")
    }

    fun timeDate(date: String): String{
        return formatDate(date, "HH:mm")
    }
}