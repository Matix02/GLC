package com.example.glc

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.glc.databinding.FragmentCurrentGameListBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CurrentGameList : Fragment() {

    private lateinit var auth: FirebaseAuth

    private var _binding: FragmentCurrentGameListBinding? = null
    private val binding get() = _binding

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCurrentGameListBinding.inflate(inflater, container, false)
        val view = binding?.root

        Log.d("Creating", "onCreateView")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Creating", "onViewCreated")
        auth = Firebase.auth
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Toast.makeText(requireContext(), "We Stay In CurrentGameList", Toast.LENGTH_LONG).show()
        } else {
            val navController = findNavController()
            navController.navigate(R.id.login_fragment)
        }
//        loginViewModel.user.observe(viewLifecycleOwner, Observer { user ->
//            if (user != null) {
//
//            } else {
//                navController.navigate(R.id.login_fragment)
//            }
//        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("Creating", "onStart")

    }

    private fun observeAuthenticationState() {
        loginViewModel.authenticationState.observe(viewLifecycleOwner, Observer {
            when (it) {

            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
//1. Zastanowić się na ideą Auth itd., czy to ma być observable itd. i co się dzieje jak jest null i WRACA, że just nie jest
//2. Przetestoweać nadawanie Auth itd. null i poszuakć w necie, czy tak uniknie się leak'ów
