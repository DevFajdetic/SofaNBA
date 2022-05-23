package com.example.sofanba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.sofanba.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    //View binding
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    //Spinner
    private val spinnerItems = arrayOf("Players", "Teams")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
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
                if (selectedSpinnerItem == "Teams") {

                } else {

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        return binding.root
    }
}