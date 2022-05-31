package com.example.sofanba.ui.favorite.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.R
import com.example.sofanba.data.model.Player
import com.example.sofanba.databinding.FragmentFavoritesBinding
import com.example.sofanba.ui.explore.viewmodel.ExploreViewModel
import com.example.sofanba.ui.favorite.adapter.FavoriteAdapter
import com.example.sofanba.ui.favorite.viewmodel.FavoriteViewModel
import com.example.weatherapp.adapters.ExploreAdapter


class FavoritesFragment : Fragment(), FavoriteAdapter.ItemClickListener {

    //View binding
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    //Adapter
    private val adapter by lazy {
        ExploreAdapter(
            requireContext(),
            arrayListOf(),
        )
    }
    //View Models
    private val viewModel: FavoriteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        //Set toolbar fragment title
        binding.appBarFavorites.title.setText(R.string.favorites_fragment_title)
        binding.appBarFavorites.appBar.inflateMenu(R.menu.favorites_edit_menu)

        binding.favoritePlayersRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritePlayersRecycler.adapter = adapter

        viewModel.allFavReponse.observe(viewLifecycleOwner) { response ->
            adapter.updateList(
                response as ArrayList<Player>,
            )
        }
        viewModel.getFavoritePlayers(requireContext())

        return binding.root
    }

    override fun onItemClicked(item: Any) {
        viewModel.removeFavoritePlayer(requireContext(), item as Player)
    }
}