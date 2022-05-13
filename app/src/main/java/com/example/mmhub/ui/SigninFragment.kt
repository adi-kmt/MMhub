package com.example.mmhub.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.mmhub.GithubRequired
import com.example.mmhub.R
import com.example.mmhub.vm.LoginVM
import com.example.mmhub.databinding.FragmentSigninBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SigninFragment : Fragment() {

    private var _binding:FragmentSigninBinding?=null
    private val binding get()= _binding!!

    private val loginVM:LoginVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return _binding!!.root
    }


    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSigninBinding.bind(view)


//        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav_view)
//        navBar?.visibility = View.GONE

        loginVM.login()
        binding.GithubLoginButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    loginVM.postStateFlow.collect{state ->
                        when(state){
                           is UIState.Success -> {
                               Log.e("Success hit", "here")
//                               val navhost = childFragmentManager.findFragmentById(R.id.fragmentContainerView2)
//                               val navcontroller = navhost?.findNavController()
//                               navcontroller?.navigate(R.id.action_signinFragment_to_repoListFragment)
                               findNavController().navigate(R.id.action_signinFragment_to_landingFragment)
                           }
                            is UIState.Loading -> Log.e("Signin Loading", "Loading data")
                            is UIState.Empty -> {
                                withContext(Dispatchers.Main){
                                    val completeURL = GithubRequired.AUTHURL + "?client_id=" + GithubRequired.CLIENT_ID + "&scope=" + GithubRequired.SCOPE + "&redirect_uri=" + GithubRequired.REDIRECT_URI + "&state=" + GithubRequired.STATE
                                    val intent = Intent(Intent.ACTION_VIEW)
                                    intent.setData(completeURL.toUri())
                                    startActivity(intent)
                                }
                            }
                            is UIState.Failure -> Log.e("Failed", state.exception.toString())
                        }
                    }

                    }
                }
        }
    }



    override fun onResume() {
        super.onResume()
        Log.e("On resume", "Already hit")
        val uri: Uri? = activity?.intent?.data
        if (uri != null && uri.toString().startsWith(GithubRequired.REDIRECT_URI)){
            Log.e("URI", uri.getQueryParameter("code").toString())
            loginVM.startLoginProcess(uri.getQueryParameter("code").toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}