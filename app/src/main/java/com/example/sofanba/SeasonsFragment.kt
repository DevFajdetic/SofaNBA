package com.example.sofanba

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sofanba.databinding.FragmentFavoritesBinding
import com.example.sofanba.databinding.FragmentSeasonsBinding

class SeasonsFragment : Fragment() {

    //View binding
    private var _binding: FragmentSeasonsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeasonsBinding.inflate(inflater, container, false)
        //Set toolbar fragment title
        binding.appBarSeasons.title.setText(R.string.seasons_fragment_title)

        return binding.root
    }
}