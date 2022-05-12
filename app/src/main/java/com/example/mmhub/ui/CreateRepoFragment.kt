package com.example.mmhub.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mmhub.R
import com.example.mmhub.databinding.FragmentCreateRepoBinding
import com.example.mmhub.databinding.FragmentRepoListBinding
import com.example.mmhub.databinding.FragmentSigninBinding
import com.example.mmhub.vm.CreateRepoVM
import com.example.mmhub.vm.RepoListVM
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateRepoFragment : Fragment() {

    private var _binding: FragmentCreateRepoBinding?=null
    private val binding get() = _binding!!

    private val createRepoVM:CreateRepoVM by viewModels()
    lateinit var name:String
    lateinit var desc:String
    var private:Boolean = false
    var template:Boolean = false

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

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCreateRepoBinding.bind(view)

        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav_view)
        navBar?.visibility = View.VISIBLE

        binding.CreateRepositoryButton.setOnClickListener {
            binding.apply {
                if ((!RepositoryName.editText?.text.isNullOrBlank()) && (!RepositoryDesc.editText?.text.isNullOrBlank())){
                    name = RepositoryNameTV.text.toString()
                    desc = RepositoryDescTV.text.toString()
                    private = PrivateSwitch.isChecked
                    template = TemplateSwitch.isChecked
                    Log.d("Not empty", "$name $desc $private $template")
                    createRepoVM.createRepo(name, desc, private, template)
                    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                        repeatOnLifecycle(Lifecycle.State.STARTED){
                            createRepoVM.createRepoStateFlow.collect{state->
                                when (state){
                                    is UIState.Failure -> Log.e("Repo Creation", "Failed")
                                    is UIState.Success -> Log.e("Repo Creation", "Succeded")
                                    else -> {Log.e("Repo Creation", "Unwanted")}
                                }
                            }
                        }
                    }
                }else {
//                    Toast.makeText(requireContext(), "Enter valid Repo name and Desc", Toast.LENGTH_LONG).show()
                    Log.d("empty", "Enter valid Repo name and Desc")
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}