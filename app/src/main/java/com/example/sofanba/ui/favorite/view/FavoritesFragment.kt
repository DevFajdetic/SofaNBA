package com.example.sofanba.ui.favorite.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sofanba.R
import com.example.sofanba.data.database.model.FavoritePlayer
import com.example.sofanba.data.database.model.FavoriteTeam
import com.example.sofanba.databinding.FragmentFavoritesBinding
import com.example.sofanba.ui.favorite.adapter.FavoriteAdapter
import com.example.sofanba.ui.favorite.helpers.ItemTouchHelperCallback
import com.example.sofanba.ui.favorite.helpers.OnStartDragListener
import com.example.sofanba.ui.favorite.viewmodel.FavoriteViewModel


class FavoritesFragment : Fragment(), FavoriteAdapter.ItemClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterPlayers: FavoriteAdapter
    private lateinit var adapterTeams: FavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel
    var itemTouchHelperPlayer: ItemTouchHelper? = null
    var itemTouchHelperTeams: ItemTouchHelper? = null

    override fun onResume() {
        super.onResume()
        viewModel.getFavoritePlayers(requireContext())
        viewModel.getFavoriteTeams(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val view = binding.root

        setupUI()
        setupViewModel()
        setupObservers()

        viewModel.getFavoritePlayers(requireContext())
        viewModel.getFavoriteTeams(requireContext())
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObservers() {
        viewModel.allFavReponseTeam.observe(viewLifecycleOwner) {
            adapterTeams.updateList(it)
            if (it.isEmpty()) {
                binding.favoriteTeamsRecycler.visibility = View.GONE
                binding.favoritesTeamsText.visibility = View.GONE
            } else if (!binding.favoriteTeamsRecycler.isVisible) {
                binding.favoriteTeamsRecycler.visibility = View.VISIBLE
                binding.favoritesTeamsText.visibility = View.VISIBLE
            }
        }
        viewModel.allFavReponsePlayer.observe(viewLifecycleOwner) {
            adapterPlayers.updateList(it)
            if (it.isEmpty()) {
                binding.favoritePlayersRecycler.visibility = View.GONE
                binding.favoritesPlayersText.visibility = View.GONE
            } else if (!binding.favoritePlayersRecycler.isVisible) {
                binding.favoritePlayersRecycler.visibility = View.VISIBLE
                binding.favoritesPlayersText.visibility = View.VISIBLE
            }
        }
    }

    private fun setupUI() {
        binding.appBarFavorites.title.setText(R.string.favorites_fragment_title)
        binding.appBarFavorites.appBar.inflateMenu(R.menu.favorites_edit_menu)
        binding.appBarFavorites.appBar.menu.findItem(R.id.favorites_edit).setOnMenuItemClickListener {
            if(it.isChecked) {
                it.setIcon(R.drawable.ic_pencil)

            } else if(!it.isChecked) {
                it.setIcon(R.drawable.ic_calendar)
                itemTouchHelperTeams!!.attachToRecyclerView(binding.favoriteTeamsRecycler)
                itemTouchHelperPlayer!!.attachToRecyclerView(binding.favoritePlayersRecycler)
            }
            return@setOnMenuItemClickListener false
        }
        binding.favoritePlayersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritePlayersRecycler.setHasFixedSize(true)
        adapterPlayers = FavoriteAdapter(
            requireContext(),
            arrayListOf(),
            itemClickListener = this,
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
                    itemTouchHelperPlayer!!.startDrag(viewHolder!!)
                }
            })
        binding.favoritePlayersRecycler.adapter = adapterPlayers
        binding.favoriteTeamsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoriteTeamsRecycler.setHasFixedSize(true)
        adapterTeams =
            FavoriteAdapter(requireContext(), arrayListOf(), this, object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder?) {
                    itemTouchHelperTeams!!.startDrag(viewHolder!!)
                }
            })
        binding.favoriteTeamsRecycler.adapter = adapterTeams
        val callbackPlayer = ItemTouchHelperCallback(adapterPlayers)
        val callbackTeam = ItemTouchHelperCallback(adapterTeams)
        itemTouchHelperPlayer = ItemTouchHelper(callbackPlayer)
        itemTouchHelperTeams = ItemTouchHelper(callbackTeam)
    }

    private fun setupViewModel() {
        viewModel = FavoriteViewModel()
    }

    override fun onItemClicked(item: Any) {
        if (item is FavoritePlayer) {
            viewModel.removeFavoritePlayer(requireContext(), item)
        } else if (item is FavoriteTeam) {
            viewModel.removeFavoriteTeam(requireContext(), item)
        }
    }
}