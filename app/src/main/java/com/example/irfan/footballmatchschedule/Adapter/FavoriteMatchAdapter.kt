package com.example.irfan.footballmatchschedule.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.Favorite
import com.example.irfan.footballmatchschedule.Utils.DateTimeConverter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteMatchAdapter(private val favorite: List<EventsMatches>, private val listener: (EventsMatches) -> Unit):
    RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FavoriteMatchAdapterUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val match_date_favorite: TextView = itemView.find(ID_DATE_FAVORITE)
        val match_time_favorite: TextView = itemView.find(ID_TIME_FAVORITE)
        val match_home_team_favorite: TextView = itemView.find(ID_HOME_TEAM_FAVORITE)
        val match_away_team_favorite: TextView = itemView.find(ID_AWAY_TEAM_FAVORITE)
        val score_home_team_favorite: TextView = itemView.find(ID_HOME_SCORE_FAVORITE)
        val score_away_team_favorite: TextView = itemView.find(ID_AWAY_SCORE_FAVORITE)

        fun bindItem(favorite: EventsMatches, listener: (EventsMatches) -> Unit){
//            match_date_favorite.text = DateTimeConverter.longDate(favorite.dateEvent!!)
            match_date_favorite.text = DateTimeConverter.longDate(favorite.dateEvent + " " + favorite.strTime)
            match_time_favorite.text = DateTimeConverter.timeDate(favorite.dateEvent +" "+ favorite.strTime + " WIB")
            match_home_team_favorite.text = favorite.strHomeTeam
            match_away_team_favorite.text = favorite.strAwayTeam
            score_home_team_favorite.text = favorite.intHomeScore
            score_away_team_favorite.text = favorite.intAwayScore

            itemView.onClick {
                listener(favorite)
            }
        }
    }

    companion object {
        val ID_DATE_FAVORITE = 1
        val ID_TIME_FAVORITE = 2
        val ID_HOME_TEAM_FAVORITE = 3
        val ID_HOME_SCORE_FAVORITE = 4
        val ID_AWAY_TEAM_FAVORITE = 5
        val ID_AWAY_SCORE_FAVORITE = 6
    }

    class FavoriteMatchAdapterUI: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout{
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    textView{
                        id = ID_DATE_FAVORITE
                        gravity = Gravity.CENTER
                        text = "Tanggal"
                    }.lparams(matchParent, wrapContent)

                    textView{
                        id = ID_TIME_FAVORITE
                        gravity = Gravity.CENTER
                        text = "Tanggal"
                    }.lparams(matchParent, wrapContent)

                    linearLayout{
                        lparams(matchParent, wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER

                        textView(){
                            id = ID_HOME_TEAM_FAVORITE
                            text = "HOME TEAM"
                            padding = dip(10)
                            gravity = Gravity.START
                        }

                        textView(){
                            id = ID_HOME_SCORE_FAVORITE
                            text = "0"
                            textSize = 15f
                            padding = dip(10)
                        }

                        textView(){
                            text = "vs"
                        }

                        textView(){
                            id = ID_AWAY_SCORE_FAVORITE
                            text = "0"
                            textSize = 15f
                            padding = dip(10)
                        }

                        textView(){
                            id = ID_AWAY_TEAM_FAVORITE
                            text = "AWAY TEAM"
                            padding = dip(10)
                            gravity = Gravity.END
                        }
                    }
                }
            }
        }
    }
}