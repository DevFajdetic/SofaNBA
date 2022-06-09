package com.example.sofanba.ui.seasons.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Size
import coil.transform.RoundedCornersTransformation
import com.example.sofanba.R
import com.example.sofanba.data.api.model.Game
import com.example.sofanba.data.api.model.Games
import com.example.sofanba.data.database.model.FavoritePlayer
import com.example.sofanba.data.database.model.FavoriteTeam
import com.example.sofanba.databinding.CardExplorePlayerBinding
import com.example.sofanba.databinding.CardExploreTeamBinding
import com.example.sofanba.databinding.CardMatchesMatchBinding
import com.example.sofanba.ui.explore.adapter.PLAYERS_VIEW
import com.example.sofanba.ui.explore.adapter.TEAMS_VIEW
import com.example.sofanba.utils.TeamsHelper
import java.util.*

class SeasonsAdapter(
    private val context: Context,
    private val gamesList: ArrayList<Game>,
) : RecyclerView.Adapter<SeasonsAdapter.MatchesViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Game>?) {
        gamesList.clear()
        if (newList != null) {
            gamesList.addAll(newList)
        }
        notifyDataSetChanged()
    }

    class MatchesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardMatchesMatchBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.card_matches_match, parent, false)
        return MatchesViewHolder(v)
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val match = gamesList[position]

        holder.binding.imageAwayTeamLogo.load(TeamsHelper.getTeamImageById(match.visitor_team.id)) {
            size(Size.ORIGINAL)
        }
        holder.binding.imageHomeTeamLogo.backgroundTintList =
            AppCompatResources.getColorStateList(context, TeamsHelper.getTeamColorById(match.home_team.id))
        holder.binding.imageHomeTeamLogo.load(TeamsHelper.getTeamImageById(match.home_team.id)) {
            size(Size.ORIGINAL)
        }
        holder.binding.imageAwayTeamLogo.backgroundTintList =
            AppCompatResources.getColorStateList(context, TeamsHelper.getTeamColorById(match.visitor_team.id))
        holder.binding.textHomeScore.text = match.home_team_score.toString()
        holder.binding.textAwayScore.text = match.visitor_team_score.toString()
        holder.binding.textHomeTeamAbbr.text = match.home_team.abbreviation
        holder.binding.textAwayTeamAbbr.text = match.visitor_team.abbreviation
        holder.binding.textMatchDate.text = TeamsHelper.convertDate(match.date)

        if(match.visitor_team_score > match.home_team_score) {
            holder.binding.textHomeScore.setTextColor(ContextCompat.getColor(context, R.color.neutrals_n_lv_2))
            holder.binding.textAwayScore.setTextColor(ContextCompat.getColor(context, R.color.black))
        }else if (match.visitor_team_score < match.home_team_score) {
            holder.binding.textAwayScore.setTextColor(ContextCompat.getColor(context, R.color.neutrals_n_lv_2))
            holder.binding.textHomeScore.setTextColor(ContextCompat.getColor(context, R.color.black))
        } else {
            holder.binding.textAwayScore.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.binding.textHomeScore.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }
}