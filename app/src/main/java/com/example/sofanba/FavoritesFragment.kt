package com.example.sofanba

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.sofanba.databinding.FragmentFavoritesBinding


class FavoritesFragment : Fragment() {

    //View binding
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        //Set toolbar fragment title
        binding.appBarFavorites.title.setText(R.string.favorites_fragment_title)
        binding.appBarFavorites.appBar.inflateMenu(R.menu.favorites_edit_menu)

        return binding.root
    }
}