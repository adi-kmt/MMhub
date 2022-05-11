package com.example.mmhub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mmhub.R
import com.example.mmhub.databinding.FragmentCreateRepoBinding
import com.example.mmhub.databinding.FragmentRepoListBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateRepoFragment : Fragment() {

    private var _binding: FragmentCreateRepoBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCreateRepoBinding.bind(view)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_graph)
        navBar.visibility = View.GONE


        binding.CreateRepositoryButton.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}