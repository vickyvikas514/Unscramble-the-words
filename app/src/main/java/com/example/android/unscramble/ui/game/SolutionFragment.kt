package com.example.android.unscramble.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.android.unscramble.R
import com.example.android.unscramble.databinding.SolutionBinding

class SolutionFragmnet: Fragment() {
    private var binding: SolutionBinding?= null
    private val sharedViewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val fragmentBinding= SolutionBinding.inflate(inflater,container,false)
        binding= fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            gameviewModel = sharedViewModel

            // Assign the fragment
            gamesolution = this@SolutionFragmnet
        }
    }
    fun GoBack(){
        findNavController().navigate(R.id.action_solution_page_to_game_fragment)
    }

}