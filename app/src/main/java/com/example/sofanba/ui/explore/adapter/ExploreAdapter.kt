package com.example.sofanba.ui.explore.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Size
import coil.transform.RoundedCornersTransformation
import com.example.sofanba.R
import com.example.sofanba.data.api.model.Player
import com.example.sofanba.data.api.model.Team
import com.example.sofanba.data.database.model.FavoritePlayer
import com.example.sofanba.data.database.model.FavoriteTeam
import com.example.sofanba.databinding.CardExplorePlayerBinding
import com.example.sofanba.databinding.CardExploreTeamBinding
import com.example.sofanba.utils.TeamsHelper.Companion.getTeamColorById
import com.example.sofanba.utils.TeamsHelper.Companion.getTeamImageById

const val PLAYERS_VIEW = 0
const val TEAMS_VIEW = 1

class ExploreAdapter(
    private val context: Context,
    private val exploreItemsList: ArrayList<Any>,
    val itemClickListener: ItemClickListener,
    private val isItemFavorite: (Any) -> Boolean,
) : Filterable, RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClicked(item: Any)
    }

    private var itemsFiltered: ArrayList<Any> = arrayListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Any>?) {
        exploreItemsList.clear()
        itemsFiltered.clear()
        if (newList != null) {
            exploreItemsList.addAll(newList)
            itemsFiltered.addAll(exploreItemsList)
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
        if (itemsFiltered[position] is Player) {
            return PLAYERS_VIEW
        } else if (itemsFiltered[position] is Team) {
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
        val player = itemsFiltered[position] as Player
        val favPlayer = FavoritePlayer(
            player.first_name,
            player.height_feet,
            player.height_inches,
            player.id,
            player.last_name,
            player.position,
            player.weight_pounds,
            position
        )
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
        holder.binding.textTeamAbbr.text = player.team.abbreviation
        holder.binding.imagePlayerFavorite.setOnClickListener {
            itemClickListener.onItemClicked(favPlayer)
        }
    }

    private fun configureTeamViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = itemsFiltered[position] as Team
        val favTeam = FavoriteTeam(
            team.abbreviation,
            team.city,
            team.conference,
            team.division,
            team.full_name,
            team.id,
            team.name,
            position
        )

        holder.binding.imageTeamLogo.load(getTeamImageById(team.id)) {
            size(Size.ORIGINAL)
        }
        holder.binding.imageTeamLogo.backgroundTintList =
            AppCompatResources.getColorStateList(context, getTeamColorById(team.id))
        holder.binding.textTeamName.text = team.full_name
        holder.binding.imageTeamFavorite.load(
            if(isItemFavorite(favTeam)) {
                R.drawable.ic_star_full
            } else R.drawable.ic_star_outline
        )
        holder.binding.imageTeamFavorite.setOnClickListener {
            itemClickListener.onItemClicked(favTeam)
        }
    }

    override fun getItemCount(): Int {
        return itemsFiltered.size
    }

    // FILTERING TEAMS WITH SEARCH
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString()?.lowercase() ?: ""
                if (charString.isEmpty()) {
                    itemsFiltered.clear()
                    itemsFiltered.addAll(exploreItemsList)
                } else {
                    val filteredList = ArrayList<Any>()
                    exploreItemsList
                        .filter {
                            it as Team
                            (it.full_name.lowercase().contains(charString))
                        }
                        .forEach { filteredList.add(it as Team) }
                    itemsFiltered = filteredList
                }
                return FilterResults().apply { values = itemsFiltered }
            }

            @Suppress("UNCHECKED_CAST")
            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(
                constraint: CharSequence?,
                results: FilterResults?
            ) {

                itemsFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Any>
                notifyDataSetChanged()
            }
        }
    }
}