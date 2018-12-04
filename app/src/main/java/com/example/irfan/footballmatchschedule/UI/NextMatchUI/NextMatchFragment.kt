package com.example.irfan.footballmatchschedule.UI.NextMatchUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Adapter.MatchAdapter
import com.example.irfan.footballmatchschedule.Model.EventsMatches
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.MatchDetailUI.MatchDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment : Fragment(), NextMatchView {

    private lateinit var presenter: NextMatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var league: LeagueItems

    private var dataSpinner: ArrayList<LeagueItems> = arrayListOf()
    private var events: MutableList<EventsMatches> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_next_match, container, false)
        progressBar = view.find(R.id.progressbarNextMatch)
        recyclerView = view.find(R.id.recylerviewNextMatch)
        spinner = view.find(R.id.spinnerNextMatch)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = NextMatchPresenter(this, ApiRepository(), Gson())
        presenter.getLeagueList()
        setLayout()
    }

    private fun setLayout(){
        adapter = MatchAdapter(events){
            toast("anda memilih ${it.idEvent}")
            startActivity<MatchDetailActivity>(MatchDetailActivity.INTENT to it)
        }
        recyclerView.adapter = adapter
    }

    override fun showListLeague(data: List<LeagueItems>) {
        dataSpinner.addAll(data)

        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getEventNext(dataSpinner.get(position).idLeague)
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
        recyclerView.invisible()
    }

    override fun hideLoading() {
        progressBar.invisible()
        recyclerView.visible()
    }

    override fun showEventList(data: List<EventsMatches>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}