package com.example.sofanba.ui.explore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.R
import com.example.sofanba.data.model.Player
import com.example.sofanba.data.model.Team
import com.example.sofanba.databinding.FragmentExploreBinding
import com.example.sofanba.ui.explore.viewmodel.ExploreViewModel
import com.example.weatherapp.adapters.ExploreAdapter

class ExploreFragment : Fragment() {

    //View binding
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    //Spinner
    private val spinnerItems = arrayOf("Players", "Teams")
    //Adapter
    private val adapter by lazy {
        ExploreAdapter(
            requireContext(),
            arrayListOf(),
        )
    }
    //View Models
    private val viewModel: ExploreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.exploreRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.exploreRecycler.adapter = adapter

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItems)
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item)
        binding.appBarExplore.exploreSpinner.adapter = spinnerAdapter

        binding.appBarExplore.exploreSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSpinnerItem = spinnerItems[position]
                if (selectedSpinnerItem == getString(R.string.teams)) {
                    binding.exploreRecyclerTitle.setText(R.string.all_teams)
                    viewModel.allTeamsResponse.observe(viewLifecycleOwner) { response ->
                        adapter.updateList(
                            response.data as ArrayList<Team>
                        )
                    }
                    viewModel.getAllTeams()
                } else if (selectedSpinnerItem == getString(R.string.players)) {
                    binding.exploreRecyclerTitle.setText(R.string.all_players)
                    viewModel.allPlayersReponse.observe(viewLifecycleOwner) { response ->
                        adapter.updateList(
                            response.data as ArrayList<Player>,
                        )
                    }
                    viewModel.getAllPlayers()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        return binding.root
    }
}