package com.example.irfan.footballmatchschedule.UI.TeamDetailOverfiewUI

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.irfan.footballmatchschedule.API.ApiRepository
import com.example.irfan.footballmatchschedule.Model.Team
import com.example.irfan.footballmatchschedule.R
import com.example.irfan.footballmatchschedule.Utils.invisible
import com.example.irfan.footballmatchschedule.Utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class TeamDetailOverfiewFragment: Fragment(), TeamDetailOverfiewView{

    private lateinit var image: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: TeamDetailOverfiewPresenter
    private lateinit var textViewName: TextView
    private lateinit var textViewYear: TextView
    private lateinit var textViewStadium: TextView
    private lateinit var textViewDesription: TextView

    private var team: MutableList<Team> = mutableListOf()

    private lateinit var id: String

    companion object {

        fun newInstance(id: String): TeamDetailOverfiewFragment {
            val fragment = TeamDetailOverfiewFragment()
            val args = Bundle()
            args.putString("id", id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewRoot = inflater.inflate(R.layout.fragment_team_detail_overfiew, container, false)

        progressBar = viewRoot.find(R.id.progressBarTeamDetail)
        image = activity!!.find(R.id.imageViewTeamDetail)
        textViewName = activity!!.findViewById(R.id.textViewTeamName)
        textViewYear = activity!!.find(R.id.textViewTeamYear)
        textViewStadium = activity!!.find(R.id.textViewTeamStadium)
        textViewDesription = viewRoot.find(R.id.textViewTeamDetailDescription)

        return viewRoot
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        id = arguments?.getString("id")!!
        presenter = TeamDetailOverfiewPresenter(this, ApiRepository(), Gson())
        presenter.getOverviewDetail(id)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showOverview(data: List<Team>) {
        team.clear()
        team.addAll(data)

        Picasso.get().load(data[0].strTeamBadge).into(image)
        textViewName.text = data[0].strTeam
        textViewYear.text = data[0].intFormedYear
        textViewStadium.text = data[0].strStadium
        textViewDesription.text = data[0].strDescriptionEN
        Log.e("berhasil", data.toString())
    }
}