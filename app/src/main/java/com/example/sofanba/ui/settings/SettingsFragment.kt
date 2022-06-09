package com.example.sofanba.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sofanba.R
import com.example.sofanba.data.database.NbaDatabase
import com.example.sofanba.databinding.FragmentSettingsBinding
import com.example.sofanba.ui.seasons.viewmodel.SeasonsViewModel
import com.example.sofanba.ui.settings.adapter.spinnerAdapter
import com.example.sofanba.ui.settings.viewmodel.SettingsViewModel


class SettingsFragment : Fragment() {

    //View binding
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        //Set toolbar fragment title
        binding.appBarSettings.title.setText(R.string.settings)
        viewModel = SettingsViewModel()
        //val dateFormats = arrayOf("31/12/2022", "12/31/2022", "2022/12/12", "Mon 12 2022", "12 Mon 2022", "31 Dec 2022", "Dec 31 2022")
        binding.dateSpinner.adapter = spinnerAdapter(requireContext(), R.layout.spinner_item_settings) //dateFormats)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val unitType = sharedPref?.getString("UnitType", "")
        if(unitType == "metric") binding.unitTypeRadioGroup.check(binding.metric.id)
        else if(unitType == "imperial") binding.unitTypeRadioGroup.check(binding.imperial.id)
        val matchDisplay = sharedPref?.getString("MatchDisplay", "")
        if(unitType == "home_vs_visitor") binding.matchDisplayRadioGroup.check(binding.homeVsVisitor.id)
        else if(unitType == "visitor_vs_home") binding.matchDisplayRadioGroup.check(binding.visitorVsHome.id)

        binding.unitTypeRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (binding.metric.isChecked) {
                if (sharedPref != null) {
                    with (sharedPref.edit()) {
                        putString("UnitType", "metric")
                        apply()
                    }
                }
            }

            if (binding.imperial.isChecked()) {
                if (sharedPref != null) {
                    with (sharedPref.edit()) {
                        putString("UnitType", "imperial")
                        apply()
                    }
                }
            }
        }

        binding.matchDisplayRadioGroup.setOnCheckedChangeListener { radioGroup, i ->
            if (binding.homeVsVisitor.isChecked) {
                if (sharedPref != null) {
                    with (sharedPref.edit()) {
                        putString("MatchDisplay", "home_vs_visitor")
                        apply()
                    }
                }
            }
            if (binding.visitorVsHome.isChecked()) {
                if (sharedPref != null) {
                    with (sharedPref.edit()) {
                        putString("MatchDisplay", "visitor_vs_home")
                        apply()
                    }
                }
            }
        }

        binding.clearButton.setOnClickListener {
            viewModel.clearTables(requireContext())
        }

        binding.moreInfoClickable.setOnClickListener {
            
        }
        return binding.root
    }
}