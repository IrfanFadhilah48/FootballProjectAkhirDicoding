package com.example.irfan.footballmatchschedule.UI.FavoriteUI

import android.content.Context
import com.example.irfan.footballmatchschedule.DB.database
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter(private val context: Context,private val view: FavoriteMatchView){

    fun showFavorite(){
        view.showLoading()

        context.database.use {
            val result = select(EventsMatches.TABLE_FAVORITES).parseList(classParser<EventsMatches>())
            view.hideLoading()
            view.displayFavoriteMatch(result)
        }

    }
}