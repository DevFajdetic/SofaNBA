package com.example.sofanba.ui.seasons.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofanba.R
import com.example.sofanba.data.api.model.Team
import com.example.sofanba.databinding.FragmentSeasonsBinding
import com.example.sofanba.ui.explore.viewmodel.ExploreViewModel
import com.example.sofanba.ui.seasons.adapter.SeasonsAdapter
import com.example.sofanba.ui.seasons.viewmodel.SeasonsViewModel
import com.example.sofanba.utils.Status
import com.google.android.material.bottomsheet.BottomSheetBehavior

class SeasonsFragment : Fragment() {

    //View binding
    private var _binding: FragmentSeasonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SeasonsViewModel
    private lateinit var adapter: SeasonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeasonsBinding.inflate(inflater, container, false)
        //Set toolbar fragment title

        setupUI()
        setupViewModel()
        setupObserver()

        viewModel.getAllGames()
        return binding.root
    }

    private fun setupObserver() {
        viewModel.allGamesResponse.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = android.view.View.GONE
                    adapter.updateList(
                        response.data?.data
                    )
                    binding.seasonsRecycler.visibility = android.view.View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = android.view.View.VISIBLE
                    binding.seasonsRecycler.visibility = android.view.View.GONE
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
        viewModel = SeasonsViewModel()
    }

    private fun setupUI() {
        binding.appBarSeasons.title.setText(R.string.seasons_fragment_title)
        binding.icFilter.setOnClickListener {
            val bottomSheet = BottomSheetDialog()
            bottomSheet.show(childFragmentManager, bottomSheet.tag)
        }
        adapter = SeasonsAdapter(requireContext(), arrayListOf())
        binding.seasonsRecycler.adapter = adapter
        binding.seasonsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }
}