package com.example.irfan.footballmatchschedule.UI.LastMatchUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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
import com.example.irfan.footballmatchschedule.UI.Match.MatchFragment
import com.example.irfan.footballmatchschedule.UI.MatchDetailUI.MatchDetailActivity
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class LastMatchFragment : Fragment(), LastMatchView{


    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var matchFragment: MatchFragment
    private lateinit var spinner: Spinner
    private lateinit var viewRoot: View

    private lateinit var league: LeagueItems
    private var dataSpinner: MutableList<LeagueItems> = mutableListOf()
    private var events: MutableList<EventsMatches> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewRoot = inflater.inflate(R.layout.fragment_last_match, container, false)
        recyclerView = viewRoot.find(R.id.recylerview)
        progressBar = viewRoot.find(R.id.progressbar)
        spinner = viewRoot.find(R.id.spinnerLastMatch)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = LastMatchPresenter(this, ApiRepository(), Gson())
        presenter.getLeagueList()
        setLayout()
    }

    override fun showLeagueList(data: List<LeagueItems>) {
        dataSpinner.addAll(data)

        viewRoot.spinnerLastMatch.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getEventsLast(dataSpinner.get(position).idLeague)
            }
        }
    }

    private fun setLayout() {
        adapter = MatchAdapter(events){
            toast("anda memilih ${it.idEvent}")
            context?.startActivity<MatchDetailActivity>(MatchDetailActivity.INTENT to it)
        }
        recyclerView.adapter = adapter
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
        Log.e("datanya", data.toString())
    }

}