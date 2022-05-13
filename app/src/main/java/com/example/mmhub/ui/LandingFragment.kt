package com.example.mmhub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mmhub.R
import com.example.mmhub.databinding.FragmentLandingBinding
import com.example.mmhub.databinding.FragmentRepoListBinding

class LandingFragment : Fragment() {
    private var _binding: FragmentLandingBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHost = childFragmentManager.findFragmentById(R.id.fragmentContainerView) as? NavHostFragment
        val navController = navHost?.navController

        navController?.let {navController ->
            binding.bottomNavBar.setupWithNavController(navController)
        }
    }
}
