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

class TeamsAdapter(private val team: List<Team>, private val listener: (Team)-> Unit): RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return team.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(team[position], listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val teamBadge: ImageView = itemView.find(R.id.team_badge)
        private val teamName: TextView = itemView.find(R.id.team_name)

        fun bindItem(teams: Team, clickListener: (Team)-> Unit){
            Picasso.get().load(teams.strTeamBadge).into(teamBadge)
            teamName.text = teams.strTeam

            itemView.setOnClickListener {
                clickListener(teams)
            }
        }
    }

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui) {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }
                }
            }
        }
    }
}