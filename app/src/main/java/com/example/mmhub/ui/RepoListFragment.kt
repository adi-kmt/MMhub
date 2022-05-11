package com.example.mmhub.ui

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.mmhub.R
import com.example.mmhub.RepoListAdapter
import com.example.mmhub.databinding.FragmentSigninBinding
import com.example.mmhub.vm.LoginVM
import com.example.mmhub.vm.RepoListVM
import com.google.android.material.bottomnavigation.BottomNavigationView

class RepoListFragment : Fragment() {
//    private var _binding:? = null
//    private val binding get() = _binding!!

    lateinit var postAdapter: RepoListAdapter

    private val repoListVM:RepoListVM by viewModels()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentRepoListBinding.inflate(inflater, container, false)
//        return _binding!!.root
//    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        _binding = FragmentRepoListBinding.bind(view)

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav_graph)
        navBar.visibility = View.VISIBLE

//        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                repoListVM.repoList.collect { state ->
//                    when (state) {
//                        is UIState.Success -> {
//                            binding.apply {
//                                Reposrv.isVisible = false
//                                progressbar.isVisible = true
//                                EmptyTv.isVisible = false
//
//                                postAdapter.setData(uiState.data)
//                            }
//                        }
//                        is UIState.Loading ->{
//                            binding.apply {
//                                Reposrv.isVisible = false
//                                progressbar.isVisible = true
//                                EmptyTv.isVisible = false
//                            }
//                        }
//                        is UIState.Empty -> {
//                            binding.apply {
//                                Reposrv.isVisible = false
//                                progressbar.isVisible = false
//                                EmptyTv.isVisible = true
//                            }
//                        }
//                        is UIState.Failure -> {
//                            binding.apply {
//                                Reposrv.isVisible = false
//                                progressbar.isVisible = false
//                                EmptyTv.isVisible = true
//                            }
//                        }
//                    }
//
//                }
//            }
//        }
    }

//
//        private fun initRV() {
//        postAdapter = RepoListAdapter(ArrayList())
//        binding.Reposrv.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = postAdapter
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//
//        _binding = null
//    }
}