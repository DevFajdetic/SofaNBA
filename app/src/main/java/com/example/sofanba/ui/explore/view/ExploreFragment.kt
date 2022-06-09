package com.example.sofanba.ui.explore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.R
import com.example.sofanba.data.api.model.Player
import com.example.sofanba.data.api.model.Team
import com.example.sofanba.data.database.model.FavoritePlayer
import com.example.sofanba.data.database.model.FavoriteTeam
import com.example.sofanba.databinding.FragmentExploreBinding
import com.example.sofanba.ui.explore.viewmodel.ExploreViewModel
import com.example.sofanba.ui.explore.adapter.ExploreAdapter
import com.example.sofanba.utils.Constants
import com.example.sofanba.utils.Status

class ExploreFragment : Fragment(), ExploreAdapter.ItemClickListener {

    //View binding
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    private val spinnerItems = arrayOf(Constants.PLAYERS, Constants.TEAMS)
    private lateinit var adapter: ExploreAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val view = binding.root

        setupUI()
        setupViewModel()
        setupObserver()

        binding.appBarExplore.exploreSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedSpinnerItem = spinnerItems[position]
                    if (selectedSpinnerItem == Constants.TEAMS) {
                        binding.exploreRecyclerTitle.setText(R.string.all_teams)
                        viewModel.getAllTeams()
                    } else if (selectedSpinnerItem == Constants.PLAYERS) {
                        binding.exploreRecyclerTitle.setText(R.string.all_players)
                        viewModel.getAllPlayers()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        binding.appBarExplore.exploreSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (binding.appBarExplore.exploreSpinner.selectedItem == getString(R.string.teams)) {
                    adapter.filter.filter(query)
                } else if (binding.appBarExplore.exploreSpinner.selectedItem == getString(R.string.players)) {
                    viewModel.getAllPlayers(search = query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (binding.appBarExplore.exploreSpinner.selectedItem == getString(R.string.teams)) {
                    adapter.filter.filter(newText)
                }
                return false
            }
        })

        return view
    }

    private fun setupObserver() {
        viewModel.allTeamsResponse.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    adapter.updateList(
                        response.data?.data as ArrayList<Team>
                    )
                    binding.exploreRecycler.visibility = android.view.View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = android.view.View.VISIBLE
                    binding.exploreRecycler.visibility = android.view.View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = android.view.View.GONE
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.allPlayersReponse.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    adapter.updateList(
                        response.data?.data as ArrayList<Player>
                    )
                    binding.exploreRecycler.visibility = android.view.View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = android.view.View.VISIBLE
                    binding.exploreRecycler.visibility = android.view.View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = android.view.View.GONE
                    Toast.makeText(requireContext(), response.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ExploreViewModel()
    }

    private fun setupUI() {
        binding.exploreRecycler.layoutManager = LinearLayoutManager(requireContext())
        adapter = ExploreAdapter(requireContext(), arrayListOf(), itemClickListener = this, isItemFavorite = { item ->
            var resp = false
            if (item is FavoriteTeam) {
               resp = viewModel.checkFavoriteTeam(requireContext(), item) == true
            } else if(item is FavoritePlayer) {
                resp = viewModel.checkFavoritePlayer(requireContext(), item) == true
            }
            return@ExploreAdapter resp
        })
        spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)
        binding.appBarExplore.exploreSpinner.adapter = spinnerAdapter
        binding.exploreRecycler.adapter = adapter
    }

    override fun onItemClicked(item: Any) {
        if (item is FavoritePlayer) {
            val checkFav = viewModel.checkFavoritePlayer(requireContext(), item)
            if (checkFav == true) {
                viewModel.removeFavoritePlayer(requireContext(), item)
            } else if (checkFav == false) {
                viewModel.insertFavoritePlayer(requireContext(), item)
            }
        } else if (item is FavoriteTeam) {
            if (viewModel.checkFavoriteTeam(requireContext(), item) == true) {
                viewModel.removeFavoriteTeam(requireContext(), item)
            } else viewModel.insertFavoriteTeam(requireContext(), item)
        }
    }
}