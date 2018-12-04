package com.example.irfan.footballmatchschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteTeamAdapter(private val favorite: List<Team>, private val listener: (Team) -> Unit):
    RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FavoriteTeamAdapterUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val team_badge: ImageView = itemView.find(ID_TEAM_BADGE)
        val team_name: TextView = itemView.find(ID_TEAM_NAME)

        fun bindItem(favorite: Team, listener: (Team) -> Unit){
            Picasso.get().load(favorite.strTeamBadge).into(team_badge)
            team_name.text = favorite.strTeam

            itemView.onClick {
                listener(favorite)
            }
        }
    }

    companion object {
        val ID_TEAM_BADGE = 1
        val ID_TEAM_NAME = 2
    }
    class FavoriteTeamAdapterUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = ID_TEAM_BADGE
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = ID_TEAM_NAME
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }
                }
            }
        }
    }
}