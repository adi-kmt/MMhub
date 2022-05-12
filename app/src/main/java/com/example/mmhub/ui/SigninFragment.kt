package com.example.mmhub.ui

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
import androidx.navigation.fragment.findNavController
import com.example.mmhub.GithubRequired
import com.example.mmhub.R
import com.example.mmhub.vm.LoginVM
import com.example.mmhub.databinding.FragmentSigninBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentSigninBinding.bind(view)


        val navBar: BottomNavigationView? = activity?.findViewById(R.id.bottom_nav_view)
        navBar?.visibility = View.GONE


        binding.GithubLoginButton.setOnClickListener {
            if (loginVM.loggedin){
                findNavController().navigate(R.id.action_signinFragment_to_repoListFragment)
            }else{
                Toast.makeText(requireContext(), "Not logged in", Toast.LENGTH_LONG).show()

                val completeURL = GithubRequired.AUTHURL + "?client_id=" + GithubRequired.CLIENT_ID + "&scope=" + GithubRequired.SCOPE + "&redirect_uri=" + GithubRequired.REDIRECT_URI + "&state=" + GithubRequired.STATE
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(completeURL.toUri())
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()

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