package com.example.mmhub.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.mmhub.R
import com.example.mmhub.databinding.FragmentCreateRepoBinding
import com.example.mmhub.databinding.FragmentRepoListBinding
import com.example.mmhub.databinding.FragmentSigninBinding
import com.example.mmhub.vm.RepoListVM
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateRepoFragment : Fragment() {

    private var _binding: FragmentCreateRepoBinding?=null
    private val binding get() = _binding!!

    private val repoListVM: RepoListVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateRepoBinding.inflate(inflater, container, false)
        return _binding?.let { bind ->
            bind.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCreateRepoBinding.bind(view)

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav_view)
        navBar?.visibility = View.VISIBLE

        binding.apply {
            if (RepositoryName.editText?.text.isNullOrBlank()){
                RepositoryName.isErrorEnabled = true
            }
            if (RepositoryDesc.editText?.text.isNullOrBlank()){
                RepositoryDesc.isErrorEnabled = true
            }
        }

        binding.CreateRepositoryButton.setOnClickListener {
            binding.apply {
                if ((!RepositoryName.editText?.text.isNullOrBlank()) && (RepositoryDesc.editText?.text.isNullOrBlank())){

                }else {
                    Toast.makeText(requireContext(), "Enter valid Repo name and Desc", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}