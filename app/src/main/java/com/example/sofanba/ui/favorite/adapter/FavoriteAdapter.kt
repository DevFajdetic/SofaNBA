package com.example.sofanba.ui.favorite.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Size
import coil.transform.RoundedCornersTransformation
import com.example.sofanba.R
import com.example.sofanba.data.model.Player
import com.example.sofanba.data.model.Team
import com.example.sofanba.databinding.CardExplorePlayerBinding
import com.example.sofanba.databinding.CardExploreTeamBinding
import com.example.sofanba.utils.TeamsHelper
import com.example.weatherapp.adapters.PLAYERS_VIEW
import com.example.weatherapp.adapters.TEAMS_VIEW


class FavoriteAdapter(
    private val context: Context,
    private val favoriteItemsList: ArrayList<Any>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemClickListener: ItemClickListener? = null
    interface ItemClickListener {
        fun onItemClicked(item: Any)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Any>?) {
        favoriteItemsList.clear()
        if (newList != null) {
            favoriteItemsList.addAll(newList)
        }
        notifyDataSetChanged()
    }

    class PlayersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardExplorePlayerBinding.bind(view)
    }

    class TeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardExploreTeamBinding.bind(view)
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    override fun getItemViewType(position: Int): Int {
        if (favoriteItemsList[position] is Player) {
            return PLAYERS_VIEW
        } else if (favoriteItemsList[position] is Team) {
            return TEAMS_VIEW
        }
        return -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            PLAYERS_VIEW -> {
                val v1: View =
                    inflater.inflate(R.layout.card_explore_player, parent, false)
                viewHolder = PlayersViewHolder(v1)
            }
            TEAMS_VIEW -> {
                val v2: View =
                    inflater.inflate(R.layout.card_explore_team, parent, false)
                viewHolder = TeamsViewHolder(v2)
            }
            else -> {
                error("On Create View Holder error!")
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            PLAYERS_VIEW -> {
                val holderPlayer = holder as PlayersViewHolder
                configurePlayerViewHolder(holderPlayer, position)
            }
            TEAMS_VIEW -> {
                val holderTeam = holder as TeamsViewHolder
                configureTeamViewHolder(holderTeam, position)
            }
            else -> {
                error("error onBind!")
            }
        }
    }

    private fun configurePlayerViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = favoriteItemsList[position] as Player

        holder.binding.imagePlayer.load(
            when (position % 3) {
                0 -> R.drawable.ic_player_1_small
                1 -> R.drawable.ic_player_2_small
                else -> {
                    R.drawable.ic_player_3_small
                }
            }
        ) {
            transformations(RoundedCornersTransformation(16F))
        }

        holder.binding.textPlayerName.text = player.formattedFullName
        holder.binding.textTeamAbbr.text = "aa"
        holder.binding.imagePlayerFavorite.load(R.drawable.ic_star_full)

        /*holder.binding.root.setOnClickListener {
            val intent = Intent(context, CityItemActivity::class.java)
            intent.putExtra("City", locationInfo)
            context.startActivity(intent)
        }*/

        holder.binding.imagePlayerFavorite.setOnClickListener{
            itemClickListener?.onItemClicked(player)
            holder.binding.imagePlayerFavorite.load(R.drawable.ic_star_outline)
        }
    }

    private fun configureTeamViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = favoriteItemsList[position] as Team

        holder.binding.imageTeamLogo.load(TeamsHelper.getTeamImageById(team.id)) {
            size(Size.ORIGINAL)
        }
        holder.binding.imageTeamLogo.backgroundTintList =
            AppCompatResources.getColorStateList(context, TeamsHelper.getTeamColorById(team.id))
        holder.binding.textTeamName.text = team.full_name
        holder.binding.imageTeamFavorite.load(R.drawable.ic_star_outline)

        /*
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, CityItemActivity::class.java)
            intent.putExtra("City", locationInfo)
            context.startActivity(intent)
        }*/
        holder.binding.imageTeamFavorite.setOnClickListener{
            itemClickListener?.onItemClicked(team)
            holder.binding.imageTeamFavorite.load(R.drawable.ic_star_outline)
        }
    }

    override fun getItemCount(): Int {
        return favoriteItemsList.size
    }
}