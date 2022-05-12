package com.example.mmhub.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.RepoData
import com.example.mmhub.R
import com.example.mmhub.RepoListAdapter
import com.example.mmhub.databinding.FragmentRepoListBinding
import com.example.mmhub.vm.RepoListVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : Fragment() {
    private var _binding:FragmentRepoListBinding?= null
    private val binding get() = _binding!!


    private val repoListVM:RepoListVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRepoListBinding.bind(view)

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav_view)
        navBar?.visibility = View.VISIBLE

        repoListVM.repoList()

        val postAdapter= RepoListAdapter()
        binding.Reposrv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                repoListVM.repoList.collect { state ->
                    when (state) {
                        is UIState.Success -> {
                            binding.apply {
                                Reposrv.isVisible = true
                                progressbar.isVisible = false
                                EmptyTv.isVisible = false
                                postAdapter.submitList(state.data)
                            }
                        }
                        is UIState.Loading ->{
                            binding.apply {
                                Reposrv.isVisible = false
                                progressbar.visibility = View.VISIBLE
                                EmptyTv.isVisible = false
                            }
                        }
                        is UIState.Empty -> {
                            binding.apply {
                                Reposrv.isVisible = false
                                progressbar.isVisible = false
                                EmptyTv.isVisible = true
                            }
                        }
                        is UIState.Failure -> {
                            binding.apply {
                                Reposrv.isVisible = false
                                progressbar.isVisible = false
                                EmptyTv.isVisible = true
                            }
                        }
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}