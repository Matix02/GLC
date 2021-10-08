package com.example.glc.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.glc.R
import com.example.glc.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    companion object {
        private const val TAG = "LoginFragment"
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var navController: NavController

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        auth = Firebase.auth

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        val signOutArgs = arguments?.getBoolean("sign_out")

        initGoogleSignInClient()

        observeAuthenticationState()

        if (signOutArgs == true) signOut()

        binding?.loginButton?.setOnClickListener { signIn() }
    }

    private fun initGoogleSignInClient() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun observeAuthenticationState() {
        loginViewModel.authenticationState.observe(viewLifecycleOwner, {
            when (it) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    navController.graph.startDestination = R.id.current_game_list
                    navController.navigate(R.id.current_game_list)
                    Log.i(TAG, " We Are in - $it")
                }
                else -> {
                    Log.i(TAG, "  $it")
                    //Do poprawienia, ustawione (2 linijki niżej, by sprawdzić NavGraph)
                    navController.graph.startDestination = R.id.current_game_list
                    navController.navigate(R.id.current_game_list)
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task?.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account?.idToken!!)
            } catch (e: ApiException) {
                Log.i(TAG, "Error in onActivityResult - $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        loginViewModel.signInWithGoogle(credential)

        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI()
                }
            }
    }

    private fun signOut() = googleSignInClient.signOut()

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun updateUI(user: FirebaseUser? = null) {
        if (user != null)
            Toast.makeText(requireContext(), "Success!!!", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(requireContext(), "Failed!!!", Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
