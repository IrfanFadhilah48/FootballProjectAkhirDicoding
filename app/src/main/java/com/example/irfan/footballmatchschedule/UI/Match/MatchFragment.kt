package com.example.irfan.footballmatchschedule.UI.Match

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TableLayout
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Adapter.ViewpagerAdapter
import com.example.irfan.footballmatchschedule.Model.LeagueItems
import com.example.irfan.footballmatchschedule.Model.ResponseLeague
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.UI.LastMatchUI.LastMatchFragment
import com.example.irfan.footballmatchschedule.UI.NextMatchUI.NextMatchFragment
import com.google.gson.Gson
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find

class MatchFragment: Fragment(){

    private lateinit var tabMatch: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var spinner: Spinner
    private lateinit var presenter: MatchPresenter

    private lateinit var league: LeagueItems

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_match, container, false)

        tabMatch = viewRoot.find(R.id.tab_matchs)
        viewPager = viewRoot.find(R.id.viewpager_match)
//        spinner = viewRoot.find(R.id.spinnerNextMatch)

//        presenter = MatchPresenter(this, ApiRepository(), Gson())
//        presenter.getLeagueList()
        setViewPager(viewPager)
        tabMatch.setupWithViewPager(viewPager)
        return viewRoot
    }

//    override fun showLeagueList(data: List<LeagueItems>) {
//        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data.le)
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                league = spinner.selectedItem as
//
//            }
//        }
//    }

//    override fun showLeagueList(data: ResponseLeague) {
//        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, data.leagues)
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                league = spinner.selectedItem as LeagueItems
//
//            }
//        }
//    }


    private fun setViewPager(viewPager: ViewPager) {
        val adapter = ViewpagerAdapter(childFragmentManager)
        adapter.addFragment(LastMatchFragment(), "Last Match")
        adapter.addFragment(NextMatchFragment(), "Next Match")
        viewPager.adapter = adapter
    }
}