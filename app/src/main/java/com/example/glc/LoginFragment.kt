package com.example.glc

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.glc.databinding.FragmentLoginBinding
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    companion object {
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var navController: NavController

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding
    // Auth Declare
    private lateinit var auth: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        //LOGIN SECTION
        //Conf Google Sing In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        //End Conf Google Sign In

        //Initialize Auth
        auth = Firebase.auth
        //End Initialize Auth
        //END LOGIN SECTION
        val signInIntent = googleSignInClient.signInIntent

        binding?.loginButton?.setOnClickListener {
            // Tutaj powinno być wywołanie z viewModel'u
            startForResult.launch(Intent(this, ))
            }

            return binding?.root
        }

    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
    }

    //LOGIN SECTION
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        //Update UI - przejdź dalej, że gotowe

    }
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent

    }
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == SIGN_IN_RESULT_CODE) {

        }
    }


    //END LOGIN SECTION

    private fun launchSignInFlow() {
        val signInIntent = googleSignInClient

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
//!!Zająć się tymi bibliotekami z Extensions czy są potrzebne
