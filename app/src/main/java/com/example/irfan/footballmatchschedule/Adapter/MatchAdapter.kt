package com.example.irfan.footballmatchschedule.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Utils.DateTimeConverter
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.*

class MatchAdapter(private val items: List<EventsMatches>, private val listener: (
        EventsMatches)-> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }


    class ViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        val match_Date : TextView = itemView.findViewById(ID_DATE)
        val match_Time : TextView = itemView.find(ID_TIME)
        val match_Home_Team : TextView = itemView.findViewById(ID_HOME_TEAM)
        val match_Away_Team : TextView = itemView.findViewById(ID_AWAY_TEAM)
        val score_Home_Team : TextView = itemView.findViewById(ID_HOME_SCORE)
        val score_Away_Team : TextView = itemView.findViewById(ID_AWAY_SCORE)

        fun bindItem(items : EventsMatches, clickListener: (EventsMatches) -> Unit){

            match_Date.text = DateTimeConverter.longDate(items.dateEvent!! + " " + items.strTime!!)
            match_Time.text = DateTimeConverter.timeDate(items.dateEvent!! +" "+ items.strTime!! + " WIB")
            match_Home_Team.text = items.strHomeTeam
            match_Away_Team.text = items.strAwayTeam
            score_Home_Team.text = items.intHomeScore
            score_Away_Team.text = items.intAwayScore

            itemView.setOnClickListener {
                clickListener(items)
            }
        }
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
//                val charString = charSequence.toString()
//                if (charString.isEmpty()) {
//                    items = filterList
//                } else {
//                    var filteredList: MutableList<EventsMatches> = mutableListOf()
//                    for (e: EventsMatches in items) {
//                        if (e.strHomeTeam?.toLowerCase()?.contains(charString)!! || e.strAwayTeam?.toLowerCase()?.contains(charString)!!) {
//                            filteredList.add(e)
//                        }
//                    }
//                    items = filteredList
//                }
//                val filterResults = Filter.FilterResults()
//                filterResults.values = items
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
//                items = filterResults.values as MutableList<EventsMatches>
//                notifyDataSetChanged()
//            }
//        }
//
//    }

    companion object {
        val ID_DATE = 1
        val ID_TIME = 2
        val ID_HOME_TEAM = 3
        val ID_HOME_SCORE = 4
        val ID_AWAY_TEAM = 5
        val ID_AWAY_SCORE = 6
    }
    class EventUI : AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout{
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    textView{
                        id = ID_DATE
                        gravity = Gravity.CENTER
                        text = "Tanggal"
                    }.lparams(matchParent, wrapContent)

                    textView{
                        id = ID_TIME
                        gravity = Gravity.CENTER
                        text = "Waktu"
                    }.lparams(matchParent, wrapContent)

                    linearLayout(){
                        lparams(matchParent, wrapContent)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER

                        textView(){
                            id = ID_HOME_TEAM
                            text = "HOME TEAM"
                            padding = dip(10)
                            gravity = Gravity.START
                        }

                        textView(){
                            id = ID_HOME_SCORE
                            text = "0"
                            textSize = 15f
                            padding = dip(10)
                        }

                        textView(){
                            text = "vs"
                        }

                        textView(){
                            id = ID_AWAY_SCORE
                            text = "0"
                            textSize = 15f
                            padding = dip(10)
                        }

                        textView(){
                            id = ID_AWAY_TEAM
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